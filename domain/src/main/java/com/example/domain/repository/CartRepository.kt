package com.example.domain.repository

import com.example.domain.model.Product

interface CartRepository {
    suspend fun getCartProductsIds(): Set<Int>
    suspend fun toggleCart(product: Product, isInCart: Boolean)
}