package com.example.pasin_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pasin_app.databinding.ActivityCriteriaBinding

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