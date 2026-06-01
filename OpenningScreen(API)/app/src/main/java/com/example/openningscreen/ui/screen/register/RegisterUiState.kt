package com.example.openningscreen.ui.screen.register

data class RegisterUiState (
    val isPasswordVisible: Boolean = false,


    val name: String = "",
    val email: String = "",
    val password: String = ""
)