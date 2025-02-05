package com.example.sneakershop.navigation

import com.example.domain.model.ProductCategory
import kotlinx.serialization.Serializable

@Serializable
sealed class NavDestination {
    @Serializable
    data object Onboarding : NavDestination()
    @Serializable
    data object Login : NavDestination()
    @Serializable
    data object Register : NavDestination()
    @Serializable
    data object Home : NavDestination()
    @Serializable
    data object Popular : NavDestination()
    @Serializable
    data class Catalogue(val category: ProductCategory) : NavDestination()
    @Serializable
    data class Details(
        val productId: Int,
        val productsIdsList: List<Int>
    ) : NavDestination()
}