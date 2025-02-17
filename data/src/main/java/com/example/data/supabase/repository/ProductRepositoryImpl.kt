package com.example.data.supabase.repository

import com.example.domain.supabase.model.Product
import com.example.domain.supabase.repository.ProductRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : ProductRepository {
    override suspend fun getAllProducts(): List<Product> {
        val columns = Columns.list("id", "title", "image", "price")
        return supabaseClient.from("product")
            .select(columns).decodeList()
    }

    override suspend fun getPopularProducts(count: Int): List<Product> {
        val columns = Columns.list("id", "title", "image", "price")
        return supabaseClient.from("product")
            .select(columns) {
                if (count > 0) limit(count.toLong())
                filter {
                    eq("isPopular", true)
                }
            }.decodeList()
    }

    override suspend fun getProductsByCategory(category: String): List<Product> {
        val columns = Columns.list("id", "title", "image", "price")
        return supabaseClient.from("product")
            .select(columns) {
                filter {
                    eq("category", category)
                }
            }.decodeList()
    }

    override suspend fun getProductById(id: Int): Product =
        supabaseClient.from("product")
            .select {
                filter {
                    eq("id", id)
                }
            }.decodeSingle()

    override suspend fun getProductsByIds(ids: List<Int>): List<Product> {
        return ids.map { id -> getProductById(id) }.take(6)
    }

    override suspend fun getProductBySearch(query: String): List<Product> {
        val columns = Columns.list("id", "title", "image", "price")
        return supabaseClient.from("product")
            .select(columns) {
                filter {
                    ilike("title", "%$query%")
                }
            }.decodeList()
    }
}