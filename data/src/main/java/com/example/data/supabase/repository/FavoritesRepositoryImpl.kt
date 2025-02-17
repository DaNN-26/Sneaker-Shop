package com.example.data.supabase.repository

import com.example.domain.supabase.model.FavoriteProduct
import com.example.domain.supabase.model.Product
import com.example.domain.supabase.repository.FavoritesRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : FavoritesRepository {

    override suspend fun getFavoritesProductsIds(): Set<Int> {
        val favoriteProducts =
            supabaseClient.from("favorites")
                .select {
                    filter {
                        eq("user_id", supabaseClient.auth.currentUserOrNull()?.id ?: "")
                    }
                }.decodeList<FavoriteProduct>()

        return favoriteProducts.map { it.productId }.toSet()
    }

    override suspend fun toggleFavorite(product: Product, isFavorite: Boolean) {
        if(!isFavorite) {
            val favoriteProduct = FavoriteProduct(
                productId = product.id,
                userId = supabaseClient.auth.currentUserOrNull()?.id ?: ""
            )
            supabaseClient.from("favorites").insert(favoriteProduct)
        } else {
            supabaseClient.from("favorites").delete {
                filter {
                    eq("user_id", supabaseClient.auth.currentUserOrNull()?.id ?: "")
                    eq("product_id", product.id)
                }
            }
        }
    }
}