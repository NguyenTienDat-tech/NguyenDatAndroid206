package com.example.openningscreen.ui.screen.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openningscreen.data.repository.UserRepository
import com.example.openningscreen.data.sharedPreference.PrefsManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: UserRepository,
    private val prefs: PrefsManager
): ViewModel() {
    //StateFlow
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()


    //Event
    private val _event = MutableSharedFlow<LoginEvent>()
    val event = _event.asSharedFlow()



    //changePassword
    fun changePassword() {
        _uiState.value = _uiState.value.copy(isPasswordVisible = !_uiState.value.isPasswordVisible)
    }

    //navigationRegister
    fun registerClick() {
        viewModelScope.launch {
            _event.emit(LoginEvent.NavigationRegister)
        }
    }


    //navigationForgot
    fun forgotCLick() {
        viewModelScope.launch {
            _event.emit(LoginEvent.NavigationForgot)
        }
    }

    //InputEmail
    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    //InputPassword
    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    //đăng nhập login
    fun onLoginClick() {
        viewModelScope.launch {
            val email = _uiState.value.email.trim()
            val password = _uiState.value.password.trim()


            //check email, pasword
            if (email.isEmpty() || password.isEmpty()) {
                _event.emit(LoginEvent.Null("Vui lòng nhập đầy đủ thông tin"))
                return@launch
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                _event.emit(LoginEvent.Null("Nhập không đúng email. Nhập lại!"))
                return@launch
            }

            //check trùng
           val success = repository.login(email, password)

            prefs.setLogin(success)

            if (success){
                _event.emit(LoginEvent.NavigationHome)
                return@launch
            }
            else {
                _event.emit(LoginEvent.Null("Tài khoản không tồn tại"))
                return@launch
            }
        }
    }

}