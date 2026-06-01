package com.example.openningscreen.data.remote.retrofitInstance

import com.example.openningscreen.core.network.ApiConstants
import com.example.openningscreen.data.remote.apiService.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val apiService by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL) //đặt địa chỉ server
            .addConverterFactory(GsonConverterFactory.create()) //Cho Retrofit biết cách xử lý JSON
            .build() //Hoàn thành tạo Retrofit
            .create(ApiService::class.java) //tạo implement cho ApiServer
    }
}