package com.example.openningscreencompose.ui.screens.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openningscreencompose.core.base.DataResult
import com.example.openningscreencompose.data.remote.model.calendar.auth.request.LoginRequest
import com.example.openningscreencompose.data.repository.AuthRepository
import com.example.openningscreencompose.data.repository.ErrorTarget
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository
): ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<LoginEvent>()
    val event = _event.asSharedFlow()


    fun onNameChange(name: String) {
        _state.value = _state.value.copy(name = name, isName = false)
    }
    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password, isPassword = false)
    }


    fun registerClick() {
        viewModelScope.launch {
            _event.emit(LoginEvent.NavigationRegister)
        }
    }


    fun onLoginClick() {
        viewModelScope.launch {
            val name = _state.value.name
            val password = _state.value.password

            val request = LoginRequest(name, password)
            val result = repository.loginUser(request)

            when (result) {
                is DataResult.Success -> {
                    _state.value = _state.value.copy(isName = false, isPassword = false, nameError = "", passwordError = "")

                    val accessToken = result.data.accessToken
                    val refreshToken = result.data.refreshToken
                    val userId = result.data.userId

                    _event.emit(LoginEvent.NavigationUserHome(accessToken, refreshToken, userId))
                }

                is DataResult.Error -> {
                    Log.d("API_Login", "message = ${result.message}")

                    _state.value = _state.value.copy(
                        isName = (result.target == ErrorTarget.NAME || result.target == ErrorTarget.GENERAL),
                        isPassword = (result.target == ErrorTarget.PASSWORD || result.target ==  ErrorTarget.GENERAL),
                        nameError = if (result.target == ErrorTarget.NAME || result.target ==  ErrorTarget.GENERAL) result.message else "",
                        passwordError = if (result.target == ErrorTarget.PASSWORD || result.target ==  ErrorTarget.GENERAL) result.message else ""
                    )
                    return@launch
                }
            }
        }
    }
}