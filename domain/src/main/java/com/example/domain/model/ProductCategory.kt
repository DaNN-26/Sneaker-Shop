package com.example.domain.model

import kotlinx.serialization.Serializable

enum class ProductCategory(val value: String) {
    ALL("Все"),
    OUTDOOR("Outdoor"),
    TENNIS("Tennis"),
    RUNNING("Running")
}