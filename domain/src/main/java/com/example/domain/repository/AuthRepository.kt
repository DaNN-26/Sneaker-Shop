package com.example.domain.repository

interface AuthRepository {
    suspend fun createAccount(
        username: String,
        email: String,
        password: String
    )

    suspend fun login(
        email: String,
        password: String
    )
}