package com.example.kotlinandroidapp1.data.remote.api

import com.example.kotlinandroidapp1.data.remote.model.LoginRequest
import com.example.kotlinandroidapp1.data.remote.model.LoginResponse
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("mobile/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}