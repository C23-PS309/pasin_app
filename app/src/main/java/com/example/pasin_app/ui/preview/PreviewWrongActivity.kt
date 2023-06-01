package com.example.pasin_app.ui.preview

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pasin_app.R
import com.example.pasin_app.databinding.ActivityPreviewWrongBinding
import com.example.pasin_app.ui.camera.CameraActivity
import com.example.pasin_app.ui.home.MainActivity
import com.example.pasin_app.utils.Preferences
import com.example.pasin_app.utils.uriToFile
import java.io.File

class PreviewWrongActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPreviewWrongBinding.inflate(layoutInflater) }
    private var file: File? = null

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

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }

        binding.btnGallery.setOnClickListener {
            Toast.makeText(this, "Silahkan pilih gambar", Toast.LENGTH_SHORT).show()
            startGallery()
        }
        binding.btnCamera.setOnClickListener {
            Toast.makeText(this, "Silahkan ambil foto", Toast.LENGTH_SHORT).show()
            startCamera()
        }

        binding.toolbarPreview.btnHome.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        binding.btnLanjut.setOnClickListener{
            Intent(this, PreviewActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    //Camera Function
    @SuppressLint("QueryPermissionsNeeded")
    private fun startCamera() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.data?.getSerializableExtra("picture", File::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.data?.getSerializableExtra("picture")
            } as? File

            if (myFile != null) {
                Preferences.saveImageCamera(myFile.path, this)
            }
            startActivity(Intent(this, PreviewActivity::class.java))
            finish()
        }
    }

    //Gallery Function
    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val selectedImg: Uri = it.data?.data as Uri
            val myFile = uriToFile(selectedImg, this)
            file = myFile
            Preferences.saveImageGallery(selectedImg.toString(), this)
            startActivity(Intent(this, PreviewActivity::class.java))
            finish()
        }
    }

    //Permission Function
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this, getString(R.string.permission_not_granted), Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 123
        const val CAMERA_X_RESULT = 200
    }
}