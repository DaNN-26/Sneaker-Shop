package com.example.domain.supabase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartProduct(
    @SerialName("user_id")
    val userId: String,
    @SerialName("product_id")
    val productId: Int,
    val quantity: Int = 1
)
