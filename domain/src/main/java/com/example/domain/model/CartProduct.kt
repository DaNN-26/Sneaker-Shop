package com.example.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartProduct(
    @SerialName("user_id")
    val userId: String,
    @SerialName("product_id")
    val productId: Int
)
