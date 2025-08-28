package com.example.kotlinandroidapp1.data.remote.model

data class LoginResponse (
    val token_tyle: String?,
    val expires_in: Int?,
    val access_token: String?,
    val errors: List<ErrorResponse>? = null
)

data  class ErrorResponse(
    val msg: String
)