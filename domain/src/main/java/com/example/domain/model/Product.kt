package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val price: Float,
    val isPopular: Boolean,
    val category: ProductCategory,
    val gender: ProductGender
)
