package com.example.sneakershop.ui.main.home

import com.example.domain.model.Product
import com.example.domain.model.ProductCategory
import com.example.sneakershop.datasource.SneakersDatasource

data class HomeState(
    val popularProducts: List<Product> = emptyList(),
    val categories: List<ProductCategory> = SneakersDatasource.categories
)
