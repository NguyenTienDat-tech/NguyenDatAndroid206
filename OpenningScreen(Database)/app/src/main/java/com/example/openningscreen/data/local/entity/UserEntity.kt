package com.example.openningscreen.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey(autoGenerate = true) //tự tăng id
    val id: Int = 0,
    val email: String,
    val password: String
)