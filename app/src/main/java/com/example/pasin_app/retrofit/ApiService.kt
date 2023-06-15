package com.example.pasin_app.retrofit

import com.example.pasin_app.model.HistoryData
import com.example.pasin_app.model.HistoryResponse
import com.example.pasin_app.model.LoginRequest
import com.example.pasin_app.model.LoginResponse
import com.example.pasin_app.model.ProcessResponse
import com.example.pasin_app.model.RegisterRequest
import com.example.pasin_app.model.RegisterResponse
import com.example.pasin_app.model.ResultResponseItem
import com.example.pasin_app.model.UpdateResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @POST("/register")
    fun registerUser(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("/login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

    @Multipart
    @POST("/history/{user_id}")
    fun processData(
        @Path("user_id") userId: String,
        @Part file: MultipartBody.Part,
        @Part("umur") umur: Float,
        @Part("tinggi") tinggi: Float,
        @Part("gender") gender: Boolean,
        @Header("Authorization") token: String
    ): Call<ProcessResponse>

    @GET ("/history/data/{detail_id}")
    fun getDetail(
        @Path("detail_id") id: String,
        @Header("Authorization") token: String
    ): Call<ArrayList<ResultResponseItem>>

    @GET ("/history/{id}")
    fun getHistory(
        @Path("id") id: String,
        @Header("Authorization") token: String
    ): Call<HistoryData>

    @PATCH("/history/data/{detail_id}")
    fun updateDetail(
        @Path("detail_id") id: String,
        @Header("Authorization") token: String,
        @Part("nama_history") nama_history: String
    ): Call<UpdateResponse>
}