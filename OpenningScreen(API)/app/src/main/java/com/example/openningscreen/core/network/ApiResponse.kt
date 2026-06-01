package com.example.openningscreen.core.network

data class ApiResponse<T> (
    val success: Boolean,
    val message: String,
    val data: T?
)