package com.example.sneakershop.ui.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getPopularProducts()
    }

    private fun getPopularProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val products = productRepository.getPopularProducts(2)
                Log.d("HomeViewmodel", products.toString())
                _state.update { it.copy(popularProducts = products) }
            } catch (e: Exception) {
                Log.d("HomeViewmodel", e.message.toString())
            }
        }
    }
}