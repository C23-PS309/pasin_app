package com.example.pasin_app.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pasin_app.databinding.ActivityMainBinding
import com.example.pasin_app.ui.preview.PreviewActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnGaleri.setOnClickListener {
            startActivity(Intent(this, PreviewActivity::class.java))
        }
        binding.btnKamera.setOnClickListener {
            startActivity(Intent(this, PreviewActivity::class.java))
        }
    }
}