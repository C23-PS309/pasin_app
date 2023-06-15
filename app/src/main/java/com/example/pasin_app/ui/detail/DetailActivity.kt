package com.example.pasin_app.ui.detail

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.pasin_app.databinding.ActivityDetailBinding
import com.example.pasin_app.model.UserPreference
import com.example.pasin_app.ui.result.ResultActivity
import com.example.pasin_app.ui.result.ResultViewModel
import com.example.pasin_app.utils.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private lateinit var resultViewModel: ResultViewModel
    val id = intent.getStringExtra(ResultActivity.EXTRA_ID).toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()

//        binding.btnEdit.setOnClickListener {
//            binding.btnEdit.animate().scaleX(1.5f).scaleY(1.5f).setDuration(200).start()
//        }

        resultViewModel.getUser().observe(this){
            val token = it.token
            resultViewModel.getResult(id, "Bearer $token")
        }

        binding.toolbarDetail.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupView() {
        resultViewModel = ViewModelProvider(
            this,
            ViewModelFactory(context = this, pref = UserPreference.getInstance(dataStore))
        )[ResultViewModel::class.java]
    }

    companion object {
       const val EXTRA_ID = "123"
    }
    
}