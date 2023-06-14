package com.example.pasin_app.ui.auth

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasin_app.model.LoginRequest
import com.example.pasin_app.model.LoginResponse
import com.example.pasin_app.model.RegisterRequest
import com.example.pasin_app.model.RegisterResponse
import com.example.pasin_app.model.UserModel
import com.example.pasin_app.model.UserPreference
import com.example.pasin_app.retrofit.ApiConfig
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthenticationViewModel(private val pref: UserPreference) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    private val _errorMessageLog = MutableLiveData<String>()
    val errorMessageLog: LiveData<String> = _errorMessageLog

    fun login(email: String, password: String) {
        _isLoading.value = true
        val request = LoginRequest(email, password)
        val client = ApiConfig.getApiService().loginUser(request)

        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful){
                    val token = response.body()?.loginResult?.token.toString()
                    val id = response.body()?.loginResult?.userId.toString()
                    viewModelScope.launch {
                        pref.login(token, id)
                    }
                    _errorMessageLog.value = "success"
                    _isLoading.value = false
                }else{
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    val errorBody = response.errorBody()?.string() ?: "Unknown error occurred"
                    val errorMessage = JSONObject(errorBody).getString("message")
                    _errorMessageLog.value = errorMessage
                    _isLoading.value = false
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
                _isLoading.value = false
            }
        })
    }

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = false
            _errorMessage.value = "success"
        }
        _isLoading.value = true
        val request = RegisterRequest(name, email, password)
        val client = ApiConfig.getApiService().registerUser(request)

        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.body()?.error == false) {
                    viewModelScope.launch {
                        _isLoading.value = false
                        _errorMessage.value = "success"
                    }
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    val errorBody = response.errorBody()?.string() ?: "Unknown error occurred"
                    val errorMessage = JSONObject(errorBody).getString("message")
                    _isLoading.value = false
                    _errorMessage.value = errorMessage
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
                val errorMessage = t.message ?: "Unknown error occurred"
                _isLoading.value = false
                _errorMessage.value = errorMessage
            }
        })
    }
}