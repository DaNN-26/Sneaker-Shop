package com.example.domain.repository

import com.example.domain.model.Product

interface FavoritesRepository {
    suspend fun getFavoritesProductsIds(): Set<Int>
    suspend fun toggleFavorite(product: Product, isFavorite: Boolean)
}