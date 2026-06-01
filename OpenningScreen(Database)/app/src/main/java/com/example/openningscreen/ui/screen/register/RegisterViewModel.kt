package com.example.openningscreen.ui.screen.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openningscreen.data.local.entity.UserEntity
import com.example.openningscreen.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: UserRepository
) : ViewModel() {
    //stateFlow
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    //eventFlow
    private val _event = MutableSharedFlow<RegisterEvent>()
    val event = _event.asSharedFlow()


    //changePassword
    fun changePassword() {
        _uiState.value = _uiState.value.copy(isPasswordVisible = !_uiState.value.isPasswordVisible)
    }

    //navigationLogin
    fun loginClick() {
        viewModelScope.launch {
            _event.emit(RegisterEvent.NavigationLogin)
        }
    }

    //inputName
    fun onNameChange(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    //inputEmail
    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    //inputPassword
    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    //đăng kí tk
    fun onRegisterClick() {
        viewModelScope.launch {
            val email = _uiState.value.email.trim()
            val password = _uiState.value.password.trim()
            val name = _uiState.value.password.trim()

            //check email, password
            if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
            _event.emit(RegisterEvent.Null("Vui lòng nhập đầy đủ thông tin"))
                return@launch
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                _event.emit(RegisterEvent.Null("Nhập không đúng email. Nhập lại!"))
                return@launch
            }

            //check trùng
            val user = UserEntity(email = email, password = password)
            val check = repository.email(user)

            if (check) {
                repository.insertUser(user)
                _event.emit(RegisterEvent.NavigationLogin)
                return@launch
            }
            else {
                _event.emit(RegisterEvent.Null("Email da ton tai!"))
                return@launch
            }

        }
    }
}