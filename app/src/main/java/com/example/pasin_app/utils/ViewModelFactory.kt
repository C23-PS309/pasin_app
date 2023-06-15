package com.example.pasin_app.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pasin_app.model.UserPreference
import com.example.pasin_app.repository.ItemRepository
import com.example.pasin_app.ui.auth.AuthenticationViewModel
import com.example.pasin_app.ui.home.MainViewModel
import com.example.pasin_app.ui.preview.PreviewViewModel
import com.example.pasin_app.ui.result.ResultViewModel

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
//            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
//                pref?.let { HistoryViewModel(it) } as T
//            }
            modelClass.isAssignableFrom(PreviewViewModel::class.java) -> {
                pref?.let { PreviewViewModel(it) } as T
            }
            modelClass.isAssignableFrom(ResultViewModel::class.java) -> {
                pref?.let { ResultViewModel(it) } as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}