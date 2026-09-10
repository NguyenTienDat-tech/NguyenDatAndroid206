package com.example.openningscreencompose.ui.screens.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.openningscreencompose.data.repository.AuthRepository

class RegisterViewModelFactory(
    private val repository: AuthRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(repository) as T
    }
}