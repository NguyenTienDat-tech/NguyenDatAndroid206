package com.example.openningscreen.ui.screen.forgotpassword

sealed class ForgotEvent {
    object NavigationOTP: ForgotEvent()
    object NavigationLogin: ForgotEvent()

    data class Null(val text: String): ForgotEvent()
    data class NavigationOTPSendEmail(val email: String): ForgotEvent()
}