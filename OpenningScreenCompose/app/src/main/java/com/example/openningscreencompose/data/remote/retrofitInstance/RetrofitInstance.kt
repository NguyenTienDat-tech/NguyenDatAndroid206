package com.example.openningscreencompose.data.remote.retrofitInstance

import com.example.openningscreencompose.core.network.ApiConstants
import com.example.openningscreencompose.data.remote.api.ApiAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstance {

    // Base Retrofit dùng để Login, Register hoặc Refresh Token
    @Provides
    @Singleton
    @Named("BaseRetrofit")
    fun provideBaseRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provide ApiAuth dùng để sử dụng Base Retrofit
    @Provides
    @Singleton
    fun provideApiAuth(@Named("BaseRetrofit") retrofit: Retrofit): ApiAuth {
        return retrofit.create(ApiAuth::class.java)
    }
}