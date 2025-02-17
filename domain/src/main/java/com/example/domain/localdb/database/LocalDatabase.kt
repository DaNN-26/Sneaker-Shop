package com.example.domain.localdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.localdb.dao.SearchQueryDao
import com.example.domain.localdb.model.SearchQuery

@Database(entities = [SearchQuery::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun searchQueryDao(): SearchQueryDao
}