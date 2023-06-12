package com.example.pasin_app.ui.preview

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        binding.imageButtonMale.setOnClickListener {
            binding.apply {
                if (genderState != "Male") {
                    imageButtonFemale.backgroundTintList = resources.getColorStateList(R.color.gray)
                    imageButtonMale.backgroundTintList = resources.getColorStateList(R.color.blue_gender)
                    genderState = "Male"
                    Log.d("Gender", genderState)
                }
            }
        }

        binding.imageButtonFemale.setOnClickListener {
            binding.apply {
                if (genderState != "Female") {
                    imageButtonMale.backgroundTintList = resources.getColorStateList(R.color.gray)
                    imageButtonFemale.backgroundTintList = resources.getColorStateList(R.color.pink)
                    genderState = "Female"
                    Log.d("Gender", genderState)
                }
            }
        }

        binding.btnProses.setOnClickListener {
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