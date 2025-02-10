package com.example.sneakershop.ui.main.catalogue

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Product
import com.example.domain.model.ProductCategory
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
class CatalogueViewmodel @Inject constructor(
    private val productRepository: ProductRepository,
    private val favoritesRepository: FavoritesRepository,
    private val cartRepository: CartRepository
) : ViewModel() {
    private val _state = MutableStateFlow(CatalogueState())
    val state = _state.asStateFlow()

    fun getProductsByCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getFavoriteAndProductsIds()
                val products = if(state.value.currentCategory != ProductCategory.ALL)
                    productRepository.getProductsByCategory(state.value.currentCategory!!.value)
                else
                    productRepository.getAllProducts()
                _state.update { it.copy(products = products) }
            } catch (e: Exception) {
                Log.d("CatalogueViewmodel", e.message.toString())
            }
        }
    }

    private suspend fun getFavoriteAndProductsIds() {
        val favorites = favoritesRepository.getFavoritesProductsIds()
        val cartProducts = cartRepository.getCartProductsIds()
        _state.update { it.copy(
            favoriteProductsIds = favorites,
            cartProductsIds = cartProducts
        ) }
    }

    fun setCategory(category: ProductCategory) {
        _state.update { it.copy(
            currentCategory = category,
            products = emptyList()
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
                Log.d("CatalogueViewmodel", e.message.toString())
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
                Log.d("CatalogueViewmodel", e.message.toString())
            }
        }
    }
}