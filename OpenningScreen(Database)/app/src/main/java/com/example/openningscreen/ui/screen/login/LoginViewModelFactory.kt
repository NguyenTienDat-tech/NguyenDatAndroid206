package com.example.openningscreen.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.openningscreen.data.repository.UserRepository
import com.example.openningscreen.data.sharedPreference.PrefsManager

class LoginViewModelFactory(
    private val repository: UserRepository,
    private val prefsManager: PrefsManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository, prefsManager) as T
    }
}