package com.example.sneakershop.ui.auth.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isError: Boolean = false,
    val emailErrorText: String = ""
)
