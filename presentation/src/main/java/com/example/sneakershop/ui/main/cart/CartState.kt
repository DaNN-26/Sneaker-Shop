package com.example.sneakershop.ui.main.cart

import com.example.domain.supabase.model.Product

data class CartState(
    val productPairs: List<Pair<Product, Int>> = emptyList(),
    val productsPrice: Float = 0f,
    val deliveryPrice: Float = 0f,
    val totalPrice: Float = 0f
)
