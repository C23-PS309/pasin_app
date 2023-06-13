package com.example.pasin_app.ui.result

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.pasin_app.R
import com.example.pasin_app.databinding.ActivityResultBinding
import com.example.pasin_app.model.UserPreference
import com.example.pasin_app.ui.history.HistoryActivity
import com.example.pasin_app.ui.home.MainActivity
import com.example.pasin_app.utils.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class ResultActivity : AppCompatActivity() {

    private val binding by lazy { ActivityResultBinding.inflate(layoutInflater) }
    private lateinit var resultViewModel: ResultViewModel
    val id = intent.getStringExtra(EXTRA_ID).toString()
    private var genderState: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()

        resultViewModel.getUser().observe(this){
            val token = it.token
            resultViewModel.getResult(id, "Bearer $token")
        }

        resultViewModel.history.observe(this){

        }

        genderState = true

        if (genderState) {
            binding.apply {
                cardView.backgroundTintList = resources.getColorStateList(R.color.blue_gender)
                imageViewGender.background = resources.getDrawable(R.drawable.male)
            }
        } else {
            binding.apply {
                cardView.backgroundTintList = resources.getColorStateList(R.color.pink)
                imageViewGender.background = resources.getDrawable(R.drawable.gender_female)
            }
        }

        binding.btnHome.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        binding.btnHistory.setOnClickListener {
            Intent(this, HistoryActivity::class.java).also {
                startActivity(it)
                finish()
            }
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