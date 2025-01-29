package com.example.sneakershop.ui.main.catalogue

import androidx.lifecycle.ViewModel
import com.example.domain.model.ProductCategory
import com.example.sneakershop.datasource.SneakersDatasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CatalogueViewmodel(
    category: ProductCategory
) : ViewModel() {
    private val _state = MutableStateFlow(CatalogueState())
    val state = _state.asStateFlow()

    init {
        setCategory(category)

    }

    private fun getProductsByCategory(category: ProductCategory) {
        val products = if(category != ProductCategory.ALL)
            SneakersDatasource.products.filter { product -> product.category == category }
        else SneakersDatasource.products
        _state.update { it.copy(products = products) }
    }

    fun setCategory(category: ProductCategory) {
        _state.update { it.copy(currentCategory = category) }
        getProductsByCategory(category)
    }
}