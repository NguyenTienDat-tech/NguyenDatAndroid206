package com.example.openningscreencompose.data.remote.api

import com.example.openningscreencompose.core.network.ApiConstants
import com.example.openningscreencompose.core.network.ApiResponse
import com.example.openningscreencompose.data.remote.model.calendar.auth.request.LoginRequest
import com.example.openningscreencompose.data.remote.model.calendar.auth.request.RegisterRequest
import com.example.openningscreencompose.data.remote.model.calendar.auth.response.LoginResponse
import com.example.openningscreencompose.data.remote.model.calendar.auth.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiAuth {
    @POST(ApiConstants.LOGIN)
    suspend fun login(@Body request: LoginRequest): ApiResponse<LoginResponse>

    @POST(ApiConstants.REGISTER)
    suspend fun register(@Body request: RegisterRequest): ApiResponse<RegisterResponse>
}