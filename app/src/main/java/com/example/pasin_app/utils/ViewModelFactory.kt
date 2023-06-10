package com.example.pasin_app.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pasin_app.model.UserPreference
import com.example.pasin_app.repository.ItemRepository
import com.example.pasin_app.ui.auth.AuthenticationViewModel
import com.example.pasin_app.ui.history.HistoryViewModel
import com.example.pasin_app.ui.home.MainViewModel

class ViewModelFactory(private val repository: ItemRepository? = null,
                       private val context: Context? = null,
                       private val pref: UserPreference? = null) : ViewModelProvider.NewInstanceFactory()
{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthenticationViewModel::class.java) -> {
                pref?.let { AuthenticationViewModel(it) } as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                pref?.let { MainViewModel(it) } as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                repository?.let { HistoryViewModel(it) } as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}