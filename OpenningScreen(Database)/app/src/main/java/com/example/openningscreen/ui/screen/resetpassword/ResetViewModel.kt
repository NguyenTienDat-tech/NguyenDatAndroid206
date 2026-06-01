package com.example.openningscreen.ui.screen.resetpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openningscreen.data.repository.UserRepository
import com.example.openningscreen.ui.screen.forgotpassword.ForgotEvent
import com.example.openningscreen.ui.screen.forgotpassword.ForgotUiState
import com.example.openningscreen.ui.screen.register.RegisterEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ResetViewModel(
    private val repository: UserRepository
) : ViewModel() {
    //stateFlow
    private val _uiState = MutableStateFlow(ResetUiState())
    private val _uiState1 = MutableStateFlow(ForgotUiState())
    val uiState = _uiState.asStateFlow()

    //event
    private val _event = MutableSharedFlow<ResetEvent>()
    val event = _event.asSharedFlow()

    //changePassword
    fun changePassword() {
        _uiState.value = _uiState.value.copy(isPasswordVisible = !_uiState.value.isPasswordVisible)
    }

    //changePassword1
    fun changePassword1() {
        _uiState.value = _uiState.value.copy(isPasswordVisible1 = !_uiState.value.isPasswordVisible1)
    }

    //navigationSuccesspassword
    fun successClick() {
        viewModelScope.launch {
            _event.emit(ResetEvent.NavigationSuccess)
        }
    }

    //navigationOTP
    fun otpClick() {
        viewModelScope.launch {
            _event.emit(ResetEvent.NavigationOTP)
        }
    }


    //inputPassword
    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    //inputPassword1
    fun onPasswordChange1(password1: String) {
        _uiState.value = _uiState.value.copy(password1 = password1)
    }

    fun onResetClick(email: String) {
        viewModelScope.launch {
            val password = _uiState.value.password.trim()
            val password1 = _uiState.value.password1.trim()

            //check email, password
            if (password.isEmpty() || password1.isEmpty()) {
                _event.emit(ResetEvent.Null("Vui lòng nhập đầy đủ thông tin"))
                return@launch
            }
            else if (password != password1) {
                _event.emit(ResetEvent.Null("Hai mật khẩu không trùng khớp"))
                return@launch
            }
            else {
                repository.updatePassword(email, password)
                _event.emit(ResetEvent.NavigationSuccess)
                return@launch
            }

        }
    }
}