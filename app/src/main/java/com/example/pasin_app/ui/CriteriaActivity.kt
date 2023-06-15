package com.example.pasin_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.pasin_app.R
import com.example.pasin_app.databinding.ActivityCriteriaBinding
import com.example.pasin_app.databinding.ActivityResultBinding
import com.example.pasin_app.ui.home.MainActivity
import com.example.pasin_app.utils.Preferences

class CriteriaActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCriteriaBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbar.btnBack.setOnClickListener {
            finish()
        }
    }
}