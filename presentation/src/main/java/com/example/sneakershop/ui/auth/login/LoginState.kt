package com.example.sneakershop.ui.auth.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isIncorrectEmail: Boolean = false,
    val isEmptyValues: Boolean = false,
    val isIncorrectData: Boolean = false
)
