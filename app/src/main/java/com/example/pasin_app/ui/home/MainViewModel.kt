package com.example.pasin_app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pasin_app.model.UserModel
import com.example.pasin_app.model.UserPreference
import kotlinx.coroutines.launch

class MainViewModel(private val pref: UserPreference? = null) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getUser(): LiveData<UserModel> {
        return (pref?.getUser()?.asLiveData() ?: UserModel("", "", "", false, "", "")) as LiveData<UserModel>
    }

    fun logout() {
        viewModelScope.launch {
            pref?.logout()
        }
    }
}