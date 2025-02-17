package com.example.sneakershop.ui.main.catalogue

import com.example.domain.supabase.model.Product
import com.example.domain.supabase.model.ProductCategory
import com.example.sneakershop.datasource.SneakersDatasource

data class CatalogueState(
    val currentCategory: ProductCategory? = null,
    val categories: List<ProductCategory> = SneakersDatasource.categories,
    val products: List<Product> = emptyList(),
    val favoriteProductsIds: Set<Int> = emptySet(),
    val cartProductsIds: Set<Int> = emptySet()
)
