package com.example.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ProductGender(val value: String) {
    @SerialName("Unisex")
    UNISEX("Unisex"),
    @SerialName("Men")
    MEN("Men's Shoes"),
    @SerialName("Women")
    WOMEN("Women's Shoes")
}