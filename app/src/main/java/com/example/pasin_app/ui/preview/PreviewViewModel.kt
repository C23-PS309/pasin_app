package com.example.pasin_app.ui.preview

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.pasin_app.model.LoginResponse
import com.example.pasin_app.model.UserModel
import com.example.pasin_app.model.UserPreference
import com.example.pasin_app.retrofit.ApiConfig
import com.example.pasin_app.utils.reduceFileImage
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class PreviewViewModel(private val pref: UserPreference) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun processData(image: File?, umur: Float, tinggi: Float, gender: Boolean, token: String){
        if(image != null){
            val file = reduceFileImage(image as File)
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "photo",
                file.name,
                requestImageFile
            )
            _isLoading.value = true
            val client = ApiConfig.getApiService().processData(imageMultipart, umur, tinggi, gender, token)
//            client.enqueue(object: Callback<ProcessResponse> {
//                override fun onResponse(
//                    call: Call<ProcessResponse>,
//                    response: Response<ProcessResponse>
//                ) {
//                    if(response.isSuccessful){
//                        _message.value = "success"
//                        _isLoading.value = false
//                    }else{
//                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
//                        val errorBody = response.errorBody()?.string() ?: "Unknown error occurred"
//                        val errorMessage = JSONObject(errorBody).getString("message")
//                        _message.value = errorMessage
//                        _isLoading.value = false
//                    }
//                }
//                override fun onFailure(call: Call<ProcessResponse>, t: Throwable) {
//                    _isLoading.value = false
//                    Log.e(ContentValues.TAG, "onFailure: ${t.message}")
//                }
//            })
        }else{
            _message.value = "Mohon masukkan gambar terlebih dahulu"
        }
    }
}