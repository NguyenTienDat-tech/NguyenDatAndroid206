package com.example.openningscreen.ui.screen.forgotpassword

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openningscreen.data.local.entity.UserEntity
import com.example.openningscreen.data.repository.UserRepository
import com.example.openningscreen.ui.screen.register.RegisterEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ForgotViewModel(
    private val repository: UserRepository
) : ViewModel() {
    //state
    private val _uiState = MutableStateFlow(ForgotUiState())
    val state = _uiState.asStateFlow()


    //event
    private val _event = MutableSharedFlow<ForgotEvent>()
    val event = _event.asSharedFlow()


    //InputEmail
    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    //navigationOTP
    fun otpClick() {
        viewModelScope.launch {
            _event.emit(ForgotEvent.NavigationOTP)
        }
    }

    //navigationLogin
    fun loginClick() {
        viewModelScope.launch {
            _event.emit(ForgotEvent.NavigationLogin)
        }
    }

    //sendEmail
    fun onSendClick() {
        viewModelScope.launch {
            val email = _uiState.value.email.trim()

            if (email.isEmpty()) {
                _event.emit(ForgotEvent.Null("Vui lòng nhập đầy đủ thông tin"))
                return@launch
            }

            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                _event.emit(ForgotEvent.Null("Nhập không đúng email. Nhập lại!"))
                return@launch
            }

            val exitEmail = repository.checkEmail(email)

            if (exitEmail) {
                _event.emit(ForgotEvent.NavigationOTPSendEmail(email))
                return@launch
            }
            else {
                _event.emit(ForgotEvent.Null("Email không tồn tại!"))
                return@launch
            }
        }
    }

}