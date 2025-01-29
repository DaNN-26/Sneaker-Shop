package com.example.sneakershop.ui.main.home

import androidx.lifecycle.ViewModel
import com.example.domain.model.Product
import com.example.sneakershop.datasource.SneakersDatasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewmodel : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getPopularProducts()
    }

    private fun getPopularProducts() {
        val popularProducts = mutableListOf<Product>()
        for(product in SneakersDatasource.products) {
            if(product.isPopular)
                popularProducts.add(product)
            if(popularProducts.size > 1) break
        }
        _state.update { it.copy(popularProducts = popularProducts) }
    }
}