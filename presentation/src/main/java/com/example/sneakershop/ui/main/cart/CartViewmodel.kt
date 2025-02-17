package com.example.sneakershop.ui.main.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.supabase.model.Product
import com.example.domain.supabase.repository.CartRepository
import com.example.domain.supabase.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewmodel @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository
) : ViewModel() {
    private val _state = MutableStateFlow(CartState())
    val state = _state.asStateFlow()

    init {
        getCartProducts()
    }

    private fun getCartProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cartProducts = cartRepository.getCartProducts()
                val quantities = cartProducts.map { it.quantity }
                val productPairs = productRepository.getProductsByIds(cartProducts.map { it.productId }).zip(quantities)
                _state.update { it.copy(productPairs = productPairs) }
            } catch (e: Exception) {
                Log.d("CartViewmodel", e.message.toString())
            }
        }
    }

    fun deleteFromCart(pair: Pair<Product, Int>)  {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                cartRepository.deleteFromCart(pair.first)
                val updatedList = state.value.productPairs.minusElement(pair)
                _state.update { it.copy(productPairs = updatedList) }
            } catch (e: Exception) {
                Log.d("CartViewmodel", e.message.toString())
            }
        }
    }

    fun addToCart(pair: Pair<Product, Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                cartRepository.addToCart(pair.first, pair.second)
                val updatedList = state.value.productPairs.map { product ->
                    if (product == pair)
                        product.copy(pair.first, pair.second + 1)
                    else
                        product
                }
                _state.update { it.copy(productPairs = updatedList) }
            } catch (e: Exception) {
                Log.d("CartViewmodel", e.message.toString())
            }
        }
    }

    fun removeFromCart(pair: Pair<Product, Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                cartRepository.removeFromCart(pair.first, pair.second)
                val updatedList = state.value.productPairs.map { product ->
                    if (product == pair)
                        product.copy(pair.first, pair.second - 1)
                    else
                        product
                }
                _state.update { it.copy(productPairs = updatedList) }
            } catch (e: Exception) {
                Log.d("CartViewmodel", e.message.toString())
            }
        }
    }

    fun calculatePrices() {
        var productsPrice = 0f
        state.value.productPairs.forEach { product ->
            productsPrice += product.first.price * product.second
        }
        val deliveryPrice = productsPrice * 0.1f
        _state.update { it.copy(
            productsPrice = productsPrice,
            deliveryPrice = deliveryPrice,
            totalPrice = productsPrice + deliveryPrice
        ) }
    }
}