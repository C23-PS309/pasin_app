package com.example.pasin_app.ui.preview

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pasin_app.databinding.ActivityPreviewBinding
import com.example.pasin_app.ui.result.ResultActivity
import com.example.pasin_app.utils.Preferences

class PreviewActivity : AppCompatActivity() {
    private val binding by lazy { ActivityPreviewBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val selectedImage = Preferences.getImageGallery(this)
        val selectedImageCamera = Preferences.getImageCamera(this)

        if (selectedImage != null) {
            binding.ivPreview.setImageURI(Uri.parse(selectedImage))
        } else if (selectedImageCamera != null) {
            binding.ivPreview.setImageBitmap(BitmapFactory.decodeFile(selectedImageCamera))
        }

        binding.btnProses.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }
}