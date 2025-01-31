package com.example.sneakershop.ui.main.catalogue

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ProductCategory
import com.example.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogueViewmodel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _state = MutableStateFlow(CatalogueState())
    val state = _state.asStateFlow()

    private fun getProductsByCategory(category: ProductCategory) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val products = if (category != ProductCategory.ALL)
                    productRepository.getProductsByCategory(category.value)
                else
                    productRepository.getAllProducts()

                    _state.update { it.copy(products = products) }
            } catch (e: Exception) {
                Log.d("CatalogueViewmodel", e.message.toString())
            }
        }
    }

    fun setCategory(category: ProductCategory) {
        _state.update { it.copy(
            currentCategory = category,
            products = emptyList()
        ) }
        getProductsByCategory(category)
    }
}