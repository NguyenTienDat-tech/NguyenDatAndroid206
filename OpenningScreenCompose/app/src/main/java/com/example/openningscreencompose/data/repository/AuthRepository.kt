package com.example.openningscreencompose.data.repository

import com.example.openningscreencompose.core.base.BaseRepository
import com.example.openningscreencompose.core.base.DataResult
import com.example.openningscreencompose.data.remote.api.ApiAuth
import com.example.openningscreencompose.data.remote.model.calendar.auth.request.LoginRequest
import com.example.openningscreencompose.data.remote.model.calendar.auth.request.RegisterRequest
import com.example.openningscreencompose.data.remote.model.calendar.auth.response.LoginResponse
import com.example.openningscreencompose.data.remote.model.calendar.auth.response.RegisterResponse

class AuthRepository(
    private val apiAuth: ApiAuth
): BaseRepository() {
    suspend fun loginUser(request: LoginRequest): DataResult<LoginResponse> {
        return safeApiCall {
            apiAuth.login(request)
        }
    }

    suspend fun registerUser(request: RegisterRequest): DataResult<RegisterResponse> {
        return safeApiCall {
            apiAuth.register(request)
        }
    }
}