package com.example.sneakershop.ui.main.popular

import com.example.domain.model.Product

data class PopularState(
    val popularProducts: List<Product> = emptyList()
)
