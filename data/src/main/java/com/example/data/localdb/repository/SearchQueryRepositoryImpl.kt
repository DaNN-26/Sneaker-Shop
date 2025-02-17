package com.example.data.localdb.repository

import com.example.domain.localdb.dao.SearchQueryDao
import com.example.domain.localdb.model.SearchQuery
import com.example.domain.localdb.repository.SearchQueryRepository
import javax.inject.Inject

class SearchQueryRepositoryImpl @Inject constructor(
    private val searchQueryDao: SearchQueryDao
) : SearchQueryRepository {
    override suspend fun getSearchQueries(): List<SearchQuery> =
        searchQueryDao.getSearchQueries()

    override suspend fun insertSearchQuery(searchQuery: SearchQuery) =
        searchQueryDao.insertSearchQuery(searchQuery)

    override suspend fun deleteSearchQuery(searchQuery: SearchQuery) =
        searchQueryDao.deleteSearchQuery(searchQuery)
}