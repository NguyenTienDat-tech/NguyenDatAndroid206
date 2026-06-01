package com.example.openningscreen.ui.screen.otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openningscreen.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OtpViewModel(
    private val repository: UserRepository
) : ViewModel() {
    //event
    private val _event = MutableSharedFlow<OtpEvent>()
    val event = _event.asSharedFlow()

    //navigationForgot
    fun forgotClick() {
        viewModelScope.launch {
            _event.emit(OtpEvent.NavigationForgot)
        }
    }


    //navigationReset
    fun resetClick(email: String) {
        viewModelScope.launch {
            _event.emit(OtpEvent.NavigationResetSendEmail(email))
        }
    }

}