package com.example.openningscreencompose.ui.screens.auth.login

sealed class LoginEvent {
    object NavigationRegister: LoginEvent()

    data class NavigationUserHome(val accessToken: String, val refreshToken: String, val userId: Int): LoginEvent()
}