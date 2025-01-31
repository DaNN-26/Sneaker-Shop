package com.example.sneakershop.ui.main.popular

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
class PopularViewmodel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _state = MutableStateFlow(PopularState())
    val state = _state.asStateFlow()

    init {
        getAllPopularProducts()
    }

    private fun getAllPopularProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val popularProducts = productRepository.getPopularProducts()
                _state.update { it.copy(popularProducts = popularProducts) }
            } catch (e: Exception) {
                Log.d("PopularViewmodel", e.message.toString())
            }
        }
    }
}