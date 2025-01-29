package com.example.sneakershop.ui.main.catalogue

import com.example.domain.model.Product
import com.example.domain.model.ProductCategory
import com.example.sneakershop.datasource.SneakersDatasource

data class CatalogueState(
    val currentCategory: ProductCategory? = null,
    val categories: List<ProductCategory> = SneakersDatasource.categories,
    val products: List<Product> = emptyList()
)
