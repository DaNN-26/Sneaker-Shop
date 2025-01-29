package com.example.sneakershop.ui.main.popular

import androidx.lifecycle.ViewModel
import com.example.sneakershop.datasource.SneakersDatasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PopularViewmodel : ViewModel() {
    private val _state = MutableStateFlow(PopularState())
    val state = _state.asStateFlow()

    init {
        getAllPopularProducts()
    }

    private fun getAllPopularProducts() {
        val popularProducts = SneakersDatasource.products.filter { product ->
            product.isPopular
        }
        _state.update { it.copy(popularProducts = popularProducts) }
    }
}