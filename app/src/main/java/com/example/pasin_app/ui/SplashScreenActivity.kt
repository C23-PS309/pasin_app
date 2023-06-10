package com.example.pasin_app.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.pasin_app.R
import com.example.pasin_app.databinding.ActivitySplashScreenBinding
import com.example.pasin_app.model.UserPreference
import com.example.pasin_app.ui.auth.LoginActivity
import com.example.pasin_app.ui.home.MainActivity
import com.example.pasin_app.ui.home.MainViewModel
import com.example.pasin_app.utils.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logoImg.alpha = 0f
        binding.logoImg.animate().setDuration(1500).alpha(1f).withEndAction{
            setupView()
            checkLogin()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }

    private fun setupView() {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(context = this, pref = UserPreference.getInstance(dataStore))
        )[MainViewModel::class.java]
    }

    private fun checkLogin() {
        mainViewModel.getUser().observe(this) { user ->
            if (user.isLogin) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
    }

}