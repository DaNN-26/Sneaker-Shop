package com.example.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ProductGender(val value: String) {
    @SerialName("Unisex")
    UNISEX("Unisex"),
    @SerialName("Men")
    MEN("Men"),
    @SerialName("Women")
    WOMEN("Women")
}