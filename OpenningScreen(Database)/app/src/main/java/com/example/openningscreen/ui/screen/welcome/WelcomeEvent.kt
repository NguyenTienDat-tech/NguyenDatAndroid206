package com.example.openningscreen.ui.screen.welcome

sealed class WelcomeEvent {
    object NavigationLogin: WelcomeEvent()
    object NavigationRegister: WelcomeEvent()
}