package com.example.pasin_app.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.example.pasin_app.R
import com.example.pasin_app.databinding.ActivityDetailBinding
import com.example.pasin_app.model.History

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    companion object {
       const val EXTRA_ID = "123"
    }
    
}