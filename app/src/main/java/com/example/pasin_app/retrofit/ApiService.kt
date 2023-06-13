package com.example.pasin_app.retrofit

import com.example.pasin_app.model.LoginRequest
import com.example.pasin_app.model.LoginResponse
import com.example.pasin_app.model.RegisterRequest
import com.example.pasin_app.model.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @POST("/register")
    fun registerUser(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("/login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

    @Multipart
    @POST("/process")
    fun processData(
        @Part file: MultipartBody.Part,
        @Part("umur") umur: Float,
        @Part("tinggi") tinggi: Float,
        @Part("gender") gender: Boolean,
        @Header("Authorization") token: String
    ): Call<LoginResponse>

    @GET ("/process")
    fun getDetail(
        @Path("id") id: String,
        @Header("Authorization") token: String
    ): Call<LoginResponse>
}