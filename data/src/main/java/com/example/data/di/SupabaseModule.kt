package com.example.data.di

import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.ProductRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModule {
    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient =
        createSupabaseClient(
            supabaseUrl = "https://dykdcjetwrryfemgwylr.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImR5a2RjamV0d3JyeWZlbWd3eWxyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mzc2NTE0NjcsImV4cCI6MjA1MzIyNzQ2N30.-FWJj3MBNhMyUjik3HPg9hgv-c7Y07wJUpQSOuUUlE0"
        ) {
            install(Postgrest)
            install(Auth)
        }

    @Provides
    @Singleton
    fun provideProductRepository(client: SupabaseClient): ProductRepository =
        ProductRepositoryImpl(client)

    @Provides
    @Singleton
    fun provideAuthRepository(client: SupabaseClient): AuthRepository =
        AuthRepositoryImpl(client.auth)
}