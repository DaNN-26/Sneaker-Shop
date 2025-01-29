package com.example.sneakershop.ui.main.home

import com.example.domain.model.Product

data class HomeState(
    val popularProducts: List<Product> = emptyList()
)
