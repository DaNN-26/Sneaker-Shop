package com.example.sneakershop.ui.main.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Product
import com.example.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewmodel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _state = MutableStateFlow(DetailsState())
    val state = _state.asStateFlow()

    fun initializeProducts(
        productId: Int,
        productsIds: List<Int>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val currentProduct = productRepository.getProductById(productId)
                val products = productRepository.getProductsByIds(productsIds)
                _state.update { it.copy(
                    currentProduct = currentProduct,
                    products = products
                ) }
            } catch (e: Exception) {
                Log.d("DetailsViewmodel", e.message.toString())
            }
        }
    }

    fun selectProduct(product: Product) {
        _state.update { it.copy(currentProduct = product) }
    }
}