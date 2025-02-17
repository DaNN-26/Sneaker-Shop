package com.example.domain.localdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.domain.localdb.model.SearchQuery

@Dao
interface SearchQueryDao {
    @Query("SELECT * FROM search_query")
    fun getSearchQueries() : List<SearchQuery>
    @Insert
    fun insertSearchQuery(searchQuery: SearchQuery)
    @Delete
    fun deleteSearchQuery(searchQuery: SearchQuery)
}