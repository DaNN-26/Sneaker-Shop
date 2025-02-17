package com.example.sneakershop.ui.main.details

import com.example.domain.supabase.model.Product

data class DetailsState(
    val currentProduct: Product? = null,
    val products: List<Product> = emptyList(),
    val favoriteProductsIds: Set<Int> = emptySet(),
    val cartProductsIds: Set<Int> = emptySet()
)
