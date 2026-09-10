package com.example.openningscreencompose.ui.screens.auth.register

sealed class RegisterEvent {
    object NavigationLogin: RegisterEvent()

    data class NavigationRegisterSendEmail(val email: String):  RegisterEvent()
}