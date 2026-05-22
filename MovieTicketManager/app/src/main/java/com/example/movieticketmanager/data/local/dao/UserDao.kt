package com.example.movieticketmanager.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movieticketmanager.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(entity: UserEntity)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    suspend fun login(email: String, password: String): UserEntity?

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun checkEmail(email: String): UserEntity?

    @Query("UPDATE users SET password = :password WHERE email = :email")
    suspend fun resetPassword(email: String, password: String)
}