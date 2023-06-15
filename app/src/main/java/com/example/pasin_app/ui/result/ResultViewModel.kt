package com.example.pasin_app.ui.result

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.pasin_app.model.History
import com.example.pasin_app.model.ResultResponse
import com.example.pasin_app.model.ResultResponseItem
import com.example.pasin_app.model.UpdateResponse
import com.example.pasin_app.model.UserModel
import com.example.pasin_app.model.UserPreference
import com.example.pasin_app.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultViewModel(private val pref: UserPreference) : ViewModel() {
    private val _history = MutableLiveData<ResultResponseItem>()
    val history: LiveData<ResultResponseItem> = _history

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
        client.enqueue(object : Callback<ArrayList<ResultResponseItem>> {
            override fun onResponse(
                call: Call<ArrayList<ResultResponseItem>>,
                response: Response<ArrayList<ResultResponseItem>>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _history.value = responseBody?.get(0)
                    }
                }else{
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<ResultResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun saveTitle(id: String, token: String, title: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().updateDetail(id, token, title)
        client.enqueue(object : Callback<UpdateResponse> {
            override fun onResponse(
                call: Call<UpdateResponse>,
                response: Response<UpdateResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    val responseBody = response.body()
                }else{
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}