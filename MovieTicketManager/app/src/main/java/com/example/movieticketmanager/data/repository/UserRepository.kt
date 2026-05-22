package com.example.movieticketmanager.data.repository

import com.example.movieticketmanager.data.local.dao.UserDao
import com.example.movieticketmanager.data.local.entity.UserEntity
import com.example.movieticketmanager.ui.screen.account.resetPassword.ResetPassword

class UserRepository(private val userDao: UserDao) {
    suspend fun login(email: String, password: String): Boolean {
        return userDao.login(email, password) != null
    }

    suspend fun insertUser(userEntity: UserEntity) {
        return userDao.insertUser(userEntity)
    }

    suspend fun check(userEntity: UserEntity): Boolean {
        val check = userDao.checkEmail(userEntity.email)

        return if (check == null) {
            true
        }
        else {
            false
        }
    }

    suspend fun checkEmail(email: String): Boolean {
        return userDao.checkEmail(email) != null
    }

    suspend fun resetPassword(email: String, password: String) {
        return userDao.resetPassword(email, password)
    }
}