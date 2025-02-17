package com.example.domain.localdb.repository

import com.example.domain.localdb.model.SearchQuery

interface SearchQueryRepository {
    suspend fun getSearchQueries() : List<SearchQuery>
    suspend fun insertSearchQuery(searchQuery: SearchQuery)
    suspend fun deleteSearchQuery(searchQuery: SearchQuery)
}