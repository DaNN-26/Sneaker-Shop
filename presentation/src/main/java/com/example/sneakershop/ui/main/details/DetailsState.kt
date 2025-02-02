package com.example.sneakershop.ui.main.details

import com.example.domain.model.Product

data class DetailsState(
    val currentProduct: Product? = null,
    val products: List<Product> = emptyList()
)
