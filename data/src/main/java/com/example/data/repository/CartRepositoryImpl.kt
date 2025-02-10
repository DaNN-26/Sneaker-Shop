package com.example.data.repository

import com.example.domain.model.CartProduct
import com.example.domain.model.Product
import com.example.domain.repository.CartRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : CartRepository {
    override suspend fun getCartProductsIds(): Set<Int> {
        val cartProducts =
            supabaseClient.from("cart")
                .select {
                    filter {
                        eq("user_id", supabaseClient.auth.currentUserOrNull()?.id ?: "")
                    }
                }.decodeList<CartProduct>()
        return cartProducts.map { it.productId }.toSet()
    }

    override suspend fun toggleCart(product: Product, isInCart: Boolean) {
        if(!isInCart) {
            val cartProduct = CartProduct(
                productId = product.id,
                userId = supabaseClient.auth.currentUserOrNull()?.id ?: ""
            )
            supabaseClient.from("cart").insert(cartProduct)
        } else {
            supabaseClient.from("cart").delete {
                filter {
                    eq("user_id", supabaseClient.auth.currentUserOrNull()?.id ?: "")
                    eq("product_id", product.id)
                }
            }
        }
    }
}