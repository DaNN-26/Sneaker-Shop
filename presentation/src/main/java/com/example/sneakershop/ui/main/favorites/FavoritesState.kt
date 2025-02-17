package com.example.sneakershop.ui.main.favorites

import com.example.domain.supabase.model.Product

data class FavoritesState(
    val favorites: List<Product> = emptyList(),
    val favoriteProductsIds: Set<Int> = emptySet(),
    val cartProductsIds: Set<Int> = emptySet()
)
