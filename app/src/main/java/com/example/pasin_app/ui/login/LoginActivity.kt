package com.example.pasin_app.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.pasin_app.R
import com.example.pasin_app.databinding.ActivityLoginBinding
import com.example.pasin_app.ui.MainActivity
import com.example.pasin_app.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
                startActivity(Intent(this, MainActivity::class.java))
                Toast.makeText(this, "Berhasil Login", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}