package com.example.openningscreen.ui.screen.login

sealed class LoginEvent {
    object NavigationRegister: LoginEvent()
    object NavigationForgot: LoginEvent()
    object NavigationHome: LoginEvent()

    data class Null(val text: String): LoginEvent()
}