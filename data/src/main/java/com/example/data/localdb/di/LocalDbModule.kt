package com.example.data.localdb.di

import android.content.Context
import androidx.room.Room
import com.example.data.localdb.repository.SearchQueryRepositoryImpl
import com.example.domain.localdb.dao.SearchQueryDao
import com.example.domain.localdb.database.LocalDatabase
import com.example.domain.localdb.repository.SearchQueryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDbModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            LocalDatabase::class.java, "local_database"
        ).build()

    @Provides
    @Singleton
    fun provideSearchQueryDao(localDatabase: LocalDatabase) =
        localDatabase.searchQueryDao()

    @Provides
    @Singleton
    fun provideSearchQueryRepository(searchQueryDao: SearchQueryDao): SearchQueryRepository =
        SearchQueryRepositoryImpl(searchQueryDao)
}