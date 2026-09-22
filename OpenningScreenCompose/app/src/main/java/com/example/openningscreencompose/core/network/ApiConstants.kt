package com.example.openningscreencompose.core.network

object ApiConstants {
    //Servers
    const val BASE_URL = "http://54.169.65.196:8080"


    //Authentication
    const val LOGOUT = "/api/v1/user/logout"
    const val RESETOTP = "/api/v1/auth/verify-otp"
    const val REGISTEROTP = "/api/v1/auth/verify-register"
    const val RESETPASSWORD = "/api/v1/auth/reset-password"
    const val REGISTER = "/api/v1/auth/register"
    const val REFRESH = "/api/v1/auth/refresh"
    const val LOGIN = "/api/v1/auth/login"
    const val FORGOTPASSWORD = "/api/v1/auth/forgot-password"
}