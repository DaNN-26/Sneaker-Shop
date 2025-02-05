package com.example.data.repository

import com.example.domain.repository.AuthRepository
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: Auth
) : AuthRepository {
    override suspend fun createAccount(username: String, email: String, password: String) {
        auth.signUpWith(Email) {
            this.email = email
            this.password = password
            this.data = buildJsonObject {
                put("username", username)
            }
        }
    }

    override suspend fun login(email: String, password: String) {
        auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }
}