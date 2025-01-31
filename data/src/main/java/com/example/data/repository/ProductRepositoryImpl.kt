package com.example.data.repository

import com.example.domain.model.Product
import com.example.domain.repository.ProductRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : ProductRepository {
    override suspend fun getAllProducts(): List<Product> =
        supabaseClient.from("product")
            .select().decodeList()

    override suspend fun getPopularProducts(count: Int): List<Product> =
        supabaseClient.from("product")
            .select {
                if(count > 0) limit(count.toLong())
                filter {
                    eq("isPopular", true)
                }
            }.decodeList()

    override suspend fun getProductsByCategory(category: String): List<Product> =
        supabaseClient.from("product")
            .select {
                filter {
                    eq("category", category)
                }
            }.decodeList()
}