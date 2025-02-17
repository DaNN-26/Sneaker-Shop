package com.example.domain.supabase.repository

import com.example.domain.supabase.model.Product

interface FavoritesRepository {
    suspend fun getFavoritesProductsIds(): Set<Int>
    suspend fun toggleFavorite(product: Product, isFavorite: Boolean)
}