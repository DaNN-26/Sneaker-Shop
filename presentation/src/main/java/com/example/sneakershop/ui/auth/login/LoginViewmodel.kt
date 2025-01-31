package com.example.sneakershop.ui.auth.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewmodel() : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun updateEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun authorize() {
        val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        if(!state.value.email.matches(emailRegex)) {
            _state.update { it.copy(
                email = "",
                password = "",
                isError = true,
                emailErrorText = "Такого email не существует"
            ) }
            throw Exception("Invalid email")
        }
    }
}