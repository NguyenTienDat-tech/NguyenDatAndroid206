package com.example.openningscreen.data.remote.apiService

import com.example.openningscreen.core.network.ApiConstants
import com.example.openningscreen.core.network.ApiResponse
import com.example.openningscreen.core.network.AuthData
import com.example.openningscreen.ui.screen.forgotpassword.requestAndResponse.ForgotRequest
import com.example.openningscreen.ui.screen.login.requestAndResponse.LoginRequest
import com.example.openningscreen.ui.screen.login.requestAndResponse.LoginResponse
import com.example.openningscreen.ui.screen.otp.requestAndResponse.OtpRequest
import com.example.openningscreen.ui.screen.register.requestAndResponse.RegisterRequest
import com.example.openningscreen.ui.screen.resetpassword.requestAndResponse.ResetRequest
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @POST(ApiConstants.LOGIN)
    suspend fun login(@Body request: LoginRequest): ApiResponse<AuthData>

    @POST(ApiConstants.REGISTER)
    suspend fun register(@Body request: RegisterRequest): ApiResponse<AuthData>

    @POST(ApiConstants.FORGOT)
    suspend fun forgot(@Body request: ForgotRequest): ApiResponse<Nothing>

    @POST(ApiConstants.OTP)
    suspend fun otp(@Body request: OtpRequest): ApiResponse<Nothing>

    @POST(ApiConstants.RESETPASSWORD)
    suspend fun reset(@Body resetRequest: ResetRequest): ApiResponse<Nothing>
}