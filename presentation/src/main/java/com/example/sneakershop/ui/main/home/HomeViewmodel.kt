package com.example.sneakershop.ui.main.home

import androidx.lifecycle.ViewModel
import com.example.domain.model.Product
import com.example.sneakershop.datasource.ProductDatasource
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
        for(product in ProductDatasource.list) {
            if(product.isPopular)
                popularProducts.add(product)
            println("added $product")
            if(popularProducts.size > 1) break
        }
        _state.update { it.copy(popularProducts = popularProducts) }
    }
}