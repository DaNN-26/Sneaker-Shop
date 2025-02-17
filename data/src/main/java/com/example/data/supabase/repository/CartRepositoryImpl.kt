package com.example.data.supabase.repository

import com.example.domain.supabase.model.CartProduct
import com.example.domain.supabase.model.Product
import com.example.domain.supabase.repository.CartRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : CartRepository {
    override suspend fun getCartProductsIds(): Set<Int> {
        val cartProducts = getCartProducts()
        return cartProducts.map { it.productId }.toSet()
    }

    override suspend fun getCartProducts(): List<CartProduct> =
        supabaseClient.from("cart")
                .select {
                    filter {
                        eq("user_id", supabaseClient.auth.currentUserOrNull()?.id ?: "")
                    }
                }.decodeList()

    override suspend fun toggleCart(product: Product, isInCart: Boolean) {
        if(!isInCart) {
            val cartProduct = CartProduct(
                productId = product.id,
                userId = supabaseClient.auth.currentUserOrNull()?.id ?: ""
            )
            supabaseClient.from("cart").insert(cartProduct)
        } else {
            deleteFromCart(product)
        }
    }

    override suspend fun deleteFromCart(product: Product) {
        supabaseClient.from("cart").delete {
            filter {
                eq("user_id", supabaseClient.auth.currentUserOrNull()?.id ?: "")
                eq("product_id", product.id)
            }
        }
    }

    override suspend fun addToCart(product: Product, quantity: Int) {
        supabaseClient.from("cart").update(
            {
                set("quantity", quantity + 1)
            }
        ) {
            filter {
                eq("user_id", supabaseClient.auth.currentUserOrNull()?.id ?: "")
                eq("product_id", product.id)
            }
        }
    }

    override suspend fun removeFromCart(product: Product, quantity: Int) {
        supabaseClient.from("cart").update(
            {
                if(quantity != 1)
                    set("quantity", quantity - 1)
                else
                    throw Exception("Quantity cannot be less than 1")
            }
        ) {
            filter {
                eq("user_id", supabaseClient.auth.currentUserOrNull()?.id ?: "")
                eq("product_id", product.id)
            }
        }
    }
}