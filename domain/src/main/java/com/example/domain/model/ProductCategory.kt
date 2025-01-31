package com.example.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ProductCategory(val value: String) {
    ALL("Все"),
    @SerialName("Outdoor")
    OUTDOOR("Outdoor"),
    @SerialName("Tennis")
    TENNIS("Tennis"),
    @SerialName("Running")
    RUNNING("Running")
}