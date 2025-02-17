package com.example.sneakershop.ui.main.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.supabase.model.Product
import com.example.domain.supabase.repository.CartRepository
import com.example.domain.supabase.repository.FavoritesRepository
import com.example.domain.supabase.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewmodel @Inject constructor(
    private val productRepository: ProductRepository,
    private val favoritesRepository: FavoritesRepository,
    private val cartRepository: CartRepository
) : ViewModel() {
    private val _state = MutableStateFlow(DetailsState())
    val state = _state.asStateFlow()

    fun initializeProducts(
        productId: Int,
        productsIds: List<Int>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getFavoriteAndCartProductsIds()
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

    private suspend fun getFavoriteAndCartProductsIds() {
        val favorites = favoritesRepository.getFavoritesProductsIds()
        val cartProducts = cartRepository.getCartProductsIds()
        _state.update { it.copy(
            favoriteProductsIds = favorites,
            cartProductsIds = cartProducts
        ) }
    }

    fun selectProduct(product: Product) {
        _state.update { it.copy(currentProduct = product) }
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
                Log.d("DetailsViewmodel", e.message.toString())
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
                Log.d("DetailsViewmodel", e.message.toString())
            }
        }
    }
}