package com.example.openningscreen.ui.screen.resetpassword.requestAndResponse

data class ResetRequest (
    val email: String,
    val otp: String,
    val newPassword: String
)