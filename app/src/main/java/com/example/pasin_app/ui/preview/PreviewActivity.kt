package com.example.pasin_app.ui.preview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pasin_app.databinding.ActivityPreviewBinding

class PreviewActivity : AppCompatActivity() {
    private val binding by lazy { ActivityPreviewBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}