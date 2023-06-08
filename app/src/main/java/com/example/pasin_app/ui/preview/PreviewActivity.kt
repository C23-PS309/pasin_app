package com.example.pasin_app.ui.preview

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pasin_app.R
import com.example.pasin_app.databinding.ActivityPreviewBinding
import com.example.pasin_app.ui.home.MainActivity
import com.example.pasin_app.ui.result.ResultActivity
import com.example.pasin_app.utils.Preferences

class PreviewActivity : AppCompatActivity() {
    private val binding by lazy { ActivityPreviewBinding.inflate(layoutInflater) }
    private var genderState: String = ""
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

        binding.imageButtonMale.setOnClickListener{
            binding.btnFemale.backgroundTintList = resources.getColorStateList(R.color.gray)
            binding.imageButtonFemale.backgroundTintList = resources.getColorStateList(R.color.gray)
            genderState = "Male"
        }

        binding.btnProses.setOnClickListener {
            Toast.makeText(this, "Gender : $genderState", Toast.LENGTH_SHORT).show()
            Intent(this, ResultActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        binding.toolbarPreview.btnHome.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}