package com.example.sneakershop.ui.auth.register

import androidx.lifecycle.ViewModel
import com.example.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewmodel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onUsernameChange(username: String) {
        _state.update { it.copy(username = username) }
    }

    fun onEmailChange(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password) }
    }

    suspend fun createAccount() {
        checkInputData()
        try {
            authRepository.createAccount(
                username = state.value.username,
                email = state.value.email,
                password = state.value.password
            )
        } catch (e: Exception) {
            _state.update { it.copy(isRegisteredEmail = true) }
            throw e
        }
    }

    private fun checkInputData() {
        if(state.value.email.isEmpty() || state.value.password.isEmpty() || state.value.username.isEmpty()) {
            _state.update { it.copy(isEmptyValues = true) }
            throw Exception("Empty values")
        }
        if(!state.value.email.isEmailFormat()) {
            _state.update { it.copy(isIncorrectEmail = true) }
            throw Exception("Invalid email")
        }
        if(state.value.password.length < 6) {
            _state.update { it.copy(isPasswordIsTooSmall = true) }
            throw Exception("Password is too small")
        }
    }

    private fun String.isEmailFormat() : Boolean {
        val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        return this.matches(emailRegex)
    }

    fun dismissDialog() {
        _state.update {
            it.copy(
                isIncorrectEmail = false,
                isEmptyValues = false,
                isRegisteredEmail = false,
                isPasswordIsTooSmall = false
            )
        }
    }
}