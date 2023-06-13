package com.example.pasin_app.ui.result

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.pasin_app.model.History
import com.example.pasin_app.model.UserModel
import com.example.pasin_app.model.UserPreference
import com.example.pasin_app.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultViewModel(private val pref: UserPreference) : ViewModel() {
    private val _history = MutableLiveData<History>()
    val history: LiveData<History> = _history

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun getResult(id: String, token: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetail(id, token)
//        client.enqueue(object : Callback<ResultResponse> {
//            override fun onResponse(
//                call: Call<ResultResponse>,
//                response: Response<ResultResponse>
//            ) {
//                _isLoading.value = false
//                if(response.isSuccessful){
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        _history.value = responseBody?.history
//                    }
//                }else{
//                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
//                _isLoading.value = false
//                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
//            }
//        })
    }
}