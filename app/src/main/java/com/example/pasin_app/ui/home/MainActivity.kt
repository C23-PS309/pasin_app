package com.example.pasin_app.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.pasin_app.R
import com.example.pasin_app.databinding.ActivityMainBinding
import com.example.pasin_app.model.UserPreference
import com.example.pasin_app.ui.camera.CameraActivity
import com.example.pasin_app.ui.history.HistoryActivity
import com.example.pasin_app.ui.auth.LoginActivity
import com.example.pasin_app.ui.preview.PreviewActivity
import com.example.pasin_app.utils.sharedPreferences
import com.example.pasin_app.utils.ViewModelFactory
import com.example.pasin_app.utils.uriToFile
import java.io.File

private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(
    name = "settings"
)

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var file: File? = null
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()

//        if (sharedPreferences.getImageGallery(this) == null){
//            binding.ivPreviewImage.setImageResource(R.drawable.contoh_foto)
//        } else {
//            binding.ivPreviewImage.setImageURI(Uri.parse(sharedPreferences.getImageGallery(this)))
//        }
//        if (selectedImage != null){
//            binding.ivPreviewImage.setImageURI(Uri.parse(selectedImage))
//        }
        binding.ivPreviewImage.setImageResource(R.drawable.contoh_foto)

        val uri = intent.getStringExtra(EXTRA_URI).toString()
        Log.d("uri Main", uri)
        if (uri != null) {
            binding.ivPreviewImage.setImageURI(Uri.parse(uri))
        }

        binding.tvNamaValue.text = sharedPreferences.getName(this)
        binding.tvUmurValue.text = sharedPreferences.getUmur(this)
        binding.tvHeightValue.text = sharedPreferences.getTinggi(this)
        binding.tvHipValue.text = sharedPreferences.getPinggul(this)
        binding.tvShoulderValue.text = sharedPreferences.getBahu(this)

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

        binding.toolbar.btnLogout.setOnClickListener {
            Toast.makeText(this, "Selamat Tinggal", Toast.LENGTH_SHORT).show()
            mainViewModel.logout()
        }

        binding.constraintLayoutHistory.setOnClickListener {
            val intentToHistory = Intent(this@MainActivity, HistoryActivity::class.java)
            startActivity(intentToHistory)
        }
    }

    private fun setupView() {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(context = this, pref = UserPreference.getInstance(dataStore))
        )[MainViewModel::class.java]

        mainViewModel.getUser().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            Log.d("ID", "onCreate: ${user.id}")
            Log.d("TOKEN", "onCreate: ${user.token}")
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
                sharedPreferences.saveImageCamera(myFile.path, this)
                val imagePath = myFile.path
                Log.d("Preferences", "Saving camera image path: $imagePath")
            }
            startActivity(Intent(this, PreviewActivity::class.java))
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
            sharedPreferences.saveImageGallery(selectedImg.toString(), this)
            startActivity(Intent(this, PreviewActivity::class.java))
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
        const val EXTRA_URI = "gallery"
    }
}