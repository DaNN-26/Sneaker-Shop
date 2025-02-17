package com.example.sneakershop.ui.main.popular

import com.example.domain.supabase.model.Product

data class PopularState(
    val popularProducts: List<Product> = emptyList(),
    val favoriteProductsIds: Set<Int> = emptySet(),
    val cartProductsIds: Set<Int> = emptySet()
)
