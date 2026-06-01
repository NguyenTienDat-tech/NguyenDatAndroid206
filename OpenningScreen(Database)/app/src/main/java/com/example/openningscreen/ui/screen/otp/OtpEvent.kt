package com.example.openningscreen.ui.screen.otp

sealed class OtpEvent {
    object NavigationForgot: OtpEvent()
    object NavigationReset: OtpEvent()

    data class NavigationResetSendEmail(val email: String): OtpEvent()
}