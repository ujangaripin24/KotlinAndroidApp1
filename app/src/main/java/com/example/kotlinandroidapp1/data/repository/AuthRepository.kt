package com.example.kotlinandroidapp1.data.repository

import android.net.http.HttpException
import com.example.kotlinandroidapp1.data.local.UserPreferences
import com.example.kotlinandroidapp1.data.remote.ApiClient
import com.example.kotlinandroidapp1.data.remote.model.LoginRequest
import com.example.kotlinandroidapp1.data.remote.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(private val userPreferences: UserPreferences) {
    private val api = ApiClient.authApi

    suspend fun login(email: String, password: String): Result<LoginResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.login(LoginRequest(email, password))
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.access_token != null) {
                        userPreferences.saveToken(body.access_token!!)
                        Result.success(body)
                    }else {
                        Result.failure(Exception("Token tidak ditemukan"))
                    }
                }else {
                    val errorBody = response.errorBody()?.string()
                    Result.failure(Exception(errorBody ?: "Login gagal"))
                }
            } catch (e: HttpException) {
                Result.failure(e)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}