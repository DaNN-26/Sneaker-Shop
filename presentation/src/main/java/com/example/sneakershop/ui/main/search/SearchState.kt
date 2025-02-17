package com.example.sneakershop.ui.main.search

import com.example.domain.localdb.model.SearchQuery
import com.example.domain.supabase.model.Product

data class SearchState(
    val query: String = "",
    val queriesList: List<SearchQuery> = emptyList(),
    val isEmptyQuery: Boolean = false,
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val favoriteProductsIds: Set<Int> = emptySet(),
    val cartProductsIds: Set<Int> = emptySet()
)
