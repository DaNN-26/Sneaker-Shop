package com.example.sneakershop.ui.auth.register

data class RegisterState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val isIncorrectEmail: Boolean = false,
    val isEmptyValues: Boolean = false,
    val isRegisteredEmail: Boolean = false,
    val isPasswordIsTooSmall: Boolean = false
)
