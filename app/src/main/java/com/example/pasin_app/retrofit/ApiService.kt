package com.example.pasin_app.retrofit

import com.example.pasin_app.model.LoginRequest
import com.example.pasin_app.model.LoginResponse
import com.example.pasin_app.model.RegisterRequest
import com.example.pasin_app.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/v1/register")
    fun registerUser(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("/v1/login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>
}