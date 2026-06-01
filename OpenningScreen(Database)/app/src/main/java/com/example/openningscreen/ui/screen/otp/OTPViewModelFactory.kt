package com.example.openningscreen.ui.screen.otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.openningscreen.data.repository.UserRepository

class OTPViewModelFactory(
    private val repository: UserRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OtpViewModel(repository) as T
    }
}