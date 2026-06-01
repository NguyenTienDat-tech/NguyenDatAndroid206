package com.example.openningscreen.data.remote.retrofitInstance

import com.example.openningscreen.data.remote.apiService.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://reqres.in/api/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) //đặt địa chỉ server
            .addConverterFactory(GsonConverterFactory.create()) //Cho Retrofit biết cách xử lý JSON
            .build() //Hoàn thành tạo Retrofit
            .create(ApiService::class.java) //tạo implement cho ApiServer
    }
}