package com.example.pasin_app.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pasin_app.databinding.ActivityDetailBinding
import com.example.pasin_app.ui.history.HistoryActivity
import com.example.pasin_app.ui.home.MainActivity

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbarDetail.btnBack.setOnClickListener {
            Intent(this, HistoryActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        binding.toolbarDetail.btnHome.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    companion object {
       const val EXTRA_ID = "123"
    }
    
}