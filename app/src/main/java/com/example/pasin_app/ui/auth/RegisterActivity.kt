package com.example.pasin_app.ui.auth

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.pasin_app.databinding.ActivityRegisterBinding
import com.example.pasin_app.model.UserModel
import com.example.pasin_app.model.UserPreference
import com.example.pasin_app.utils.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class RegisterActivity : AppCompatActivity() {

    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    private lateinit var authViewModel: AuthenticationViewModel
    private lateinit var user: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupViewModel()

        // Button Register
        binding.btnRegister.setOnClickListener {
            val name = binding.edFulllNameRegister.text.toString()
            val emailUser = binding.edEmailRegister.text.toString()
            val passwordUser = binding.edPasswordRegister.text.toString()
            val confirmPasswordUser = binding.edConfirmPasswordRegister.text.toString()

            if (emailUser.isEmpty() || passwordUser.isEmpty() || confirmPasswordUser.isEmpty()) {
                if (emailUser.isEmpty()) {
                    binding.edEmailRegister.error = "Email tidak boleh kosong"
                    Toast.makeText(this, "Emailnya diisi dong!", Toast.LENGTH_SHORT).show()
                }
                if (passwordUser.isEmpty()) {
                    binding.edPasswordRegister.error = "Password tidak boleh kosong"
                    Toast.makeText(this, "Passwordnya diisi dong!", Toast.LENGTH_SHORT).show()
                }
                if (confirmPasswordUser.isEmpty()) {
                    binding.edConfirmPasswordRegister.error = "Konfirmasi Password tidak boleh kosong"
                }
            } else if (passwordUser.length < 8) {
                binding.edPasswordRegister.error = "Password minimal 8 karakter"
            } else if (passwordUser != confirmPasswordUser) {
                binding.edConfirmPasswordRegister.error = "Password tidak sama"
            } else {
                authViewModel.register(name, emailUser, passwordUser)
            }
        }

        authViewModel.errorMessage.observe(this) { errorMessage ->
            if (errorMessage == "success") {
                AlertDialog.Builder(this).apply {
                    setTitle("Signup Success!")
                    setMessage("Your account has been created. Please login.")
                    setPositiveButton("Ok") { _, _ ->
                        finish()
                    }
                    create()
                    show()
                }
            }else if (errorMessage.isNotEmpty()){
                binding.edEmailRegister.error = errorMessage
                AlertDialog.Builder(this).apply {
                    setTitle("Account Creation Failed")
                    setMessage(errorMessage)
                    setPositiveButton("Ok") { _, _ ->

                    }
                    create()
                    show()
                }
            }
        }

        // Button ke Login
        val startLoginActivity = View.OnClickListener {
            finish()
        }
        binding.tvSudahPunyaAkun.setOnClickListener(startLoginActivity)
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