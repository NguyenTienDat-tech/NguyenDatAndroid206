package com.example.openningscreen.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.openningscreen.data.local.entity.UserEntity
import com.example.openningscreen.data.local.dao.UserDao

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}