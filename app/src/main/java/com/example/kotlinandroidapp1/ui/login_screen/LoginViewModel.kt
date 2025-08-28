package com.example.kotlinandroidapp1.ui.login_screen

import com.example.kotlinandroidapp1.data.remote.model.LoginResponse
import com.mapbox.base.common.logger.model.Message

sealed class LoginUiState {
    object Idle: LoginUiState()
    object Loading: LoginUiState()
    data class Success(val response: LoginResponse) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

class LoginViewModel {
}