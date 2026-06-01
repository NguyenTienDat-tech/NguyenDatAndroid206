package com.example.openningscreen.ui.screen.register

sealed class RegisterEvent {
    object NavigationLogin: RegisterEvent()

    data class Null(val text: String): RegisterEvent()
}