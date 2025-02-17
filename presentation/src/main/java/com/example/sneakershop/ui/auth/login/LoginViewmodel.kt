package com.example.sneakershop.ui.auth.login

import androidx.lifecycle.ViewModel
import com.example.domain.supabase.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewmodel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun updateEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    suspend fun login() {
        checkInputData()
        try {
            authRepository.login(
                email = state.value.email,
                password = state.value.password
            )
        } catch (e: Exception) {
            _state.update { it.copy(isIncorrectData = true) }
            throw e
        }
    }

    private fun checkInputData() {
        if(state.value.email.isEmpty() || state.value.password.isEmpty()) {
            _state.update { it.copy(isEmptyValues = true) }
            throw Exception("Empty values")
        }
        if(!state.value.email.isEmailFormat()) {
            _state.update { it.copy(isIncorrectEmail = true) }
            throw Exception("Invalid email")
        }
    }

    private fun String.isEmailFormat() : Boolean {
        val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        return this.matches(emailRegex)
    }

    fun dismissDialog() {
        _state.update { it.copy(
            isIncorrectEmail = false,
            isEmptyValues = false,
            isIncorrectData = false
        ) }
    }
}