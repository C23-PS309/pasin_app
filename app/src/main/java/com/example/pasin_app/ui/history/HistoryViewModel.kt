package com.example.pasin_app.ui.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.pasin_app.model.HistoryResponseItem
import com.example.pasin_app.model.UserModel
import com.example.pasin_app.model.UserPreference
import com.example.pasin_app.retrofit.ApiConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryViewModel(private val pref: UserPreference) : ViewModel() {

    private val _groupedItems = MutableStateFlow(List<HistoryResponseItem>(0) {
        HistoryResponseItem(
            "",0f,0f,0f,"","",
        )
    })
    val groupedItems: StateFlow<List<HistoryResponseItem>> = _groupedItems

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessageLog = MutableLiveData<String>()
    val errorMessageLog: LiveData<String> = _errorMessageLog

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun getHistoryDataUser(id: String, token: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getHistory(id, token)

        client.enqueue(object : Callback<List<HistoryResponseItem>>{
            override fun onResponse(
                call: Call<List<HistoryResponseItem>>,
                response: Response<List<HistoryResponseItem>>
            ) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    Log.d("Check Data", "Data: $responseData")
                    _errorMessageLog.value = "success"
                    _isLoading.value = false

                    if (responseData != null) {
                        _groupedItems.value = responseData
                        Log.d("HistoryViewModel", "Data riwayat diperbarui: $responseData")
                    } else {
                        _errorMessageLog.value = "Data is empty"
                    }

                } else {
                    _errorMessageLog.value = response.message()
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<List<HistoryResponseItem>>, t: Throwable) {
                _errorMessageLog.value = t.message
                _isLoading.value = false
            }

        })
    }
}
