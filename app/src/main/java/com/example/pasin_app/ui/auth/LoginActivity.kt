package com.example.pasin_app.ui.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.pasin_app.databinding.ActivityLoginBinding
import com.example.pasin_app.model.UserModel
import com.example.pasin_app.model.UserPreference
import com.example.pasin_app.ui.home.MainActivity
import com.example.pasin_app.utils.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private lateinit var authViewModel: AuthenticationViewModel
    private lateinit var user: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupViewModel()

        // Button Login
        binding.btnLogin.setOnClickListener {
            val emailUser = binding.edEmailLogin.text.toString()
            val passwordUser = binding.edPasswordLogin.text.toString()

            if (emailUser.isEmpty() || passwordUser.isEmpty()) {
                if (emailUser.isEmpty()) {
                    binding.edEmailLogin.error = "Email tidak boleh kosong"
                    Toast.makeText(this, "Emailnya diisi dong!", Toast.LENGTH_SHORT).show()
                }
                if (passwordUser.isEmpty()) {
                    binding.edPasswordLogin.error = "Password tidak boleh kosong"
                    Toast.makeText(this, "Passwordnya diisi dong!", Toast.LENGTH_SHORT).show()
                }
            } else if (passwordUser.length >= 8) {
                authViewModel.login(emailUser, passwordUser)
//                startActivity(Intent(this, MainActivity::class.java))
//                Toast.makeText(this, "Berhasil Login", Toast.LENGTH_SHORT).show()
//                finish()
            }

            authViewModel.errorMessageLog.observe(this){
                when (it) {
                    "success" -> {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                        binding.edEmailLogin.error = ""
                        binding.edPasswordLogin.error = ""
                    }
                    "User not found" -> {
                        binding.edEmailLogin.error = "Wrong email"
                        binding.edPasswordLogin.error = ""
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    }
                    "Invalid password" -> {
                        binding.edEmailLogin.error = ""
                        binding.edPasswordLogin.error = "Wrong password"
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        // Button ke register
        val startRegisterActivity = View.OnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.tvDontHaveAccount.setOnClickListener(startRegisterActivity)
    }

    private fun setupViewModel() {
        authViewModel = ViewModelProvider(
            this,
            ViewModelFactory(context = this, pref = UserPreference.getInstance(dataStore))
        )[AuthenticationViewModel::class.java]

        authViewModel.getUser().observe(this) { user ->
            this.user = user
        }
    }
}