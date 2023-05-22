package com.example.pasin_app.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pasin_app.R
import com.example.pasin_app.databinding.ActivityRegisterBinding
import com.example.pasin_app.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Button Register
        binding.btnRegister.setOnClickListener {
            val emailUser = binding.edEmailRegister.text.toString()
            val passwordUser = binding.edPasswordRegister.text.toString()
            val confirmPasswordUser = binding.edConfirmPasswordRegister.text.toString()

            if (emailUser.isEmpty() || passwordUser.isEmpty() || confirmPasswordUser.isEmpty()) {
                if (emailUser.isEmpty()) {
                    binding.edEmailRegister.error = "Email tidak boleh kosong"
                }
                if (passwordUser.isEmpty()) {
                    binding.edPasswordRegister.error = "Kosong"
                }
                if (confirmPasswordUser.isEmpty()) {
                    binding.edConfirmPasswordRegister.error = "Konfirmasi Password tidak boleh kosong"
                }
            } else if (passwordUser.length < 8) {
                binding.edPasswordRegister.error = "Password minimal 8 karakter"
            } else if (passwordUser != confirmPasswordUser) {
                binding.edConfirmPasswordRegister.error = "Password tidak sama"
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        // Button ke Login
        val startLoginActivity = View.OnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        binding.tvSudahPunyaAkun.setOnClickListener(startLoginActivity)
    }
}