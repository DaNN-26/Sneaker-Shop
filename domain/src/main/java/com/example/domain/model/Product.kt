package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int = 0,
    val title: String = "Кроссовки",
    val description: String = "",
    val image: String = "",
    val price: Float = 0.0f,
    val isPopular: Boolean = true,
    val category: ProductCategory = ProductCategory.ALL,
    val gender: ProductGender = ProductGender.UNISEX
)
