package com.example.openningscreen.ui.screen.success

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SuccessViewModel : ViewModel(){
    private val _event = MutableSharedFlow<SuccessEvent>()
    val event = _event.asSharedFlow()

    fun loginClick() {
        viewModelScope.launch {
            _event.emit(SuccessEvent.NavigationLogin)
        }
    }
}