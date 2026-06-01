package com.example.openningscreen.data.remote.apiService

import com.example.openningscreen.ui.screen.login.LoginRequest
import com.example.openningscreen.ui.screen.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("x-api-key: free_user_3CtrN4ahgV5DS3Jrz0ilR7t3YZZ")
    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse
}