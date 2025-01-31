package com.example.domain.repository

import com.example.domain.model.Product

interface ProductRepository {
    suspend fun getAllProducts(): List<Product>
    suspend fun getPopularProducts(count: Int = 0): List<Product>
    suspend fun getProductsByCategory(category: String): List<Product>
}