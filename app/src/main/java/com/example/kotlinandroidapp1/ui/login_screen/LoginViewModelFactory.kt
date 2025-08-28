package com.example.kotlinandroidapp1.ui.login_screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinandroidapp1.data.local.UserPreferences
import com.example.kotlinandroidapp1.data.repository.AuthRepository

class LoginViewModelFactory(context: Context) : ViewModelProvider.Factory {
    private val repository = AuthRepository(UserPreferences(context))

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}
