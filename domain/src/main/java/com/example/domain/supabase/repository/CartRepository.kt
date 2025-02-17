package com.example.domain.supabase.repository

import com.example.domain.supabase.model.CartProduct
import com.example.domain.supabase.model.Product

interface CartRepository {
    suspend fun getCartProductsIds(): Set<Int>
    suspend fun getCartProducts(): List<CartProduct>
    suspend fun toggleCart(product: Product, isInCart: Boolean)
    suspend fun deleteFromCart(product: Product)
    suspend fun addToCart(product: Product, quantity: Int)
    suspend fun removeFromCart(product: Product, quantity: Int)
}