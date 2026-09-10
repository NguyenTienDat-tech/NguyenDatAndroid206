package com.example.openningscreencompose.data.remote.retrofitInstance

import com.example.openningscreencompose.core.network.ApiConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private var authenticatedRetrofit: Retrofit? = null

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}