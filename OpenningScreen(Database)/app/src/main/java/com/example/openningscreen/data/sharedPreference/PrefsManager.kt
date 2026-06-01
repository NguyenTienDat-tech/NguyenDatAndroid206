package com.example.openningscreen.data.sharedPreference

import android.content.Context

class PrefsManager(context: Context) {
    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE) //MODE_PRIVATE: chỉ app mình mới dùng được

    //ghi vào true/false
    fun setLogin(isLogin: Boolean) {
        prefs.edit().putBoolean("login", isLogin).apply()
    }

    //nhận giá trị trên thông qua "login" nếu null thì trả về false
    fun getLogin(): Boolean {
        return prefs.getBoolean("login", false)
    }

    fun setUsername(isName: String) {
        prefs.edit().putString("user", isName).apply()
    }

    fun getUserName(): String {
        return prefs.getString("user", "") ?: ""
    }

    //xóa tất cả dữ liệu
    fun logout() {
        prefs.edit().clear().apply()
    }
}