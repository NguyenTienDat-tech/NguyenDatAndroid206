package com.example.openningscreen.core.network

import com.example.openningscreen.ui.screen.register.requestAndResponse.RegisterResponse

data class AuthData (
    val user: RegisterResponse,
    val token: String
)