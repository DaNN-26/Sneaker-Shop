package com.example.sneakershop.ui.main.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Product
import com.example.domain.repository.CartRepository
import com.example.domain.repository.FavoritesRepository
import com.example.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewmodel @Inject constructor(
    private val productRepository: ProductRepository,
    private val favoritesRepository: FavoritesRepository,
    private val cartRepository: CartRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    fun getProductsBySearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getFavoriteAndCartProductsIds()
                _state.update { it.copy(isLoading = true) }
                val products = productRepository.getProductBySearch(query)
                _state.update { it.copy(
                    products = products,
                    isLoading = false
                ) }
            } catch (e: Exception) {
                Log.d("SearchViewmodel", e.message.toString())
            }
        }
    }

    fun onQueryChange(query: String) {
        _state.update { it.copy(query = query) }
    }

    suspend fun getFavoriteAndCartProductsIds() {
        val favorites = favoritesRepository.getFavoritesProductsIds()
        val cartProducts = cartRepository.getCartProductsIds()
        _state.update { it.copy(
            favoriteProductsIds = favorites,
            cartProductsIds = cartProducts
        ) }
    }

    fun toggleFavorite(product: Product) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val isFavorite = state.value.favoriteProductsIds.contains(product.id)
                favoritesRepository.toggleFavorite(product, isFavorite)

                _state.update { it.copy(
                    favoriteProductsIds = if(isFavorite)
                        state.value.favoriteProductsIds - product.id
                    else
                        state.value.favoriteProductsIds + product.id
                ) }
            } catch (e: Exception) {
                Log.d("PopularViewmodel", e.message.toString())
            }
        }
    }

    fun toggleCart(product: Product) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val isInCart = state.value.cartProductsIds.contains(product.id)
                cartRepository.toggleCart(product, isInCart)

                _state.update { it.copy(
                    cartProductsIds = if(isInCart)
                        state.value.cartProductsIds - product.id
                    else
                        state.value.cartProductsIds + product.id
                ) }
            } catch (e: Exception) {
                Log.d("SearchViewmodel", e.message.toString())
            }
        }
    }
}