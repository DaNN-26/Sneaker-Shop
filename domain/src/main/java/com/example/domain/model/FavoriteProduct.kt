package com.example.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteProduct(
    @SerialName("product_id")
    val productId: Int,
    @SerialName("user_id")
    val userId: String
)
