package com.example.openningscreen.data.repository

import com.example.openningscreen.data.remote.apiService.ApiService
import com.example.openningscreen.data.remote.retrofitInstance.RetrofitInstance
import com.example.openningscreen.ui.screen.forgotpassword.ForgotEvent
import com.example.openningscreen.ui.screen.forgotpassword.requestAndResponse.ForgotRequest
import com.example.openningscreen.ui.screen.login.requestAndResponse.LoginRequest
import com.example.openningscreen.ui.screen.otp.requestAndResponse.OtpRequest
import com.example.openningscreen.ui.screen.register.requestAndResponse.RegisterRequest
import com.example.openningscreen.ui.screen.resetpassword.requestAndResponse.ResetRequest

class UserRepository(
    private val apiService: ApiService,
) {

    suspend fun login(email: String, password: String): Boolean {
        return try {
            val response = apiService.login(LoginRequest(email, password))

            return response.success
        } catch (e: Exception) {
            false
        }
    }

    suspend fun register(username: String, email: String, password: String): Boolean {
        return try {
            val response = apiService.register(RegisterRequest(username, email, password))

            return response.success
        } catch (e: Exception) {
            false
        }
    }

    suspend fun forgot(email: String): Boolean {
        return try {
            val response = apiService.forgot(ForgotRequest(email))

            return response.success
        } catch (e: Exception) {
            false
        }
    }

    suspend fun otp(email: String, otp: String): Boolean {
        return try {
            val response = apiService.otp(OtpRequest(email, otp))

            return response.success
        } catch (e: Exception) {
            false
        }
    }

    suspend fun resetpassword(email: String, otp: String, newPassword: String): Boolean {
        return try {
            val response = apiService.reset(ResetRequest(email, otp, newPassword))

            return response.success
        } catch (e: Exception) {
            false
        }
    }
}