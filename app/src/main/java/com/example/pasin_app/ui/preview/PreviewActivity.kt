package com.example.pasin_app.ui.preview

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.pasin_app.R
import com.example.pasin_app.databinding.ActivityPreviewBinding
import com.example.pasin_app.model.UserPreference
import com.example.pasin_app.ui.CriteriaActivity
import com.example.pasin_app.ui.detail.DetailActivity
import com.example.pasin_app.ui.home.MainActivity
import com.example.pasin_app.ui.result.ResultActivity
import com.example.pasin_app.utils.Preferences
import com.example.pasin_app.utils.ViewModelFactory
import com.example.pasin_app.utils.uriToFile
import java.io.File

private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(
    name = "settings"
)

class PreviewActivity : AppCompatActivity() {
    private val binding by lazy { ActivityPreviewBinding.inflate(layoutInflater) }
    private var genderState: String = ""
    private var umurUser: Float = 0f
    private var tinggiUser: Float = 0f
    private lateinit var previewViewModel: PreviewViewModel
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()

        val selectedImage = Preferences.getImageGallery(this)
        val selectedImageCamera = Preferences.getImageCamera(this)

        if (selectedImage != null) {
            binding.ivPreview.setImageURI(Uri.parse(selectedImage))
        } else if (selectedImageCamera != null) {
            binding.ivPreview.setImageBitmap(BitmapFactory.decodeFile(selectedImageCamera))
        }

        binding.button.setOnClickListener {
            val intent = Intent(this, CriteriaActivity::class.java)
            startActivity(intent)
        }
        // Set gender
        binding.imageButtonMale.setOnClickListener {
            binding.apply {
                if (genderState != "Male") {
                    imageButtonFemale.backgroundTintList = resources.getColorStateList(R.color.gray)
                    imageButtonMale.backgroundTintList =
                        resources.getColorStateList(R.color.blue_gender)
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

            Log.d("Gender", genderState)
            val gender: Boolean = genderState == "Male"

            when {
                binding.etUmur.text == null -> {
                    binding.etUmur.error = "Umur tidak boleh kosong"
                }

                binding.etTinggi.text == null -> {
                    binding.etTinggi.error = "Tinggi tidak boleh kosong"
                }

                genderState == "" -> {
                    binding.imageButtonMale.backgroundTintList =
                        resources.getColorStateList(R.color.gray)
                    binding.imageButtonFemale.backgroundTintList =
                        resources.getColorStateList(R.color.gray)
                }

                else -> {

//                    umurUser = binding.etUmur.text.toString().toFloat()
//                    tinggiUser = binding.etTinggi.text.toString().toFloat()

                    val imageUri: String? = Preferences.getImageGallery(this)
                    val image: File? =
                        if (imageUri != null) uriToFile(Uri.parse(imageUri), this) else null

                    handler = Handler()
                    showLoading(true)

                    handler.postDelayed({
                        Intent(this, ResultActivity::class.java).also {
                            intent.putExtra(DetailActivity.EXTRA_ID, "ini adalah id")
                            startActivity(it)
                            finish()
                        }
                        showLoading(false)
                    }, 2000)

//                    previewViewModel.getUser().observe(this){
//                        val token = it.token
//                        previewViewModel.processData(image, umurUser, tinggiUser, gender, "Bearer $token", it.id)
//                    }
                }
            }
        }

        binding.toolbarPreview.btnHome.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("Batalkan Proses")
                setMessage("Apakah Anda Yakin Ingin Membatalkan Proses?")
                setPositiveButton("Ya") { _, _ ->
                    Preferences.deleteImageCamera(this@PreviewActivity)
                    Preferences.deleteImageGallery(this@PreviewActivity)
                    Intent(this@PreviewActivity, MainActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                }
                setNegativeButton("Tidak") { _, _ -> }
                create()
                show()
            }
        }
//        resultObserver()
    }

    private fun setupView() {
        previewViewModel = ViewModelProvider(
            this,
            ViewModelFactory(context = this, pref = UserPreference.getInstance(dataStore))
        )[PreviewViewModel::class.java]
    }

//    private fun resultObserver() {
//        previewViewModel.message.observe(this) {
//            when(it){
//                "success" -> {
//                    Intent(this, ResultActivity::class.java).also {
//                        intent.putExtra(DetailActivity.EXTRA_ID, "ini adalah id")
//                        startActivity(it)
//                        finish()
//                    }
//                }
//                else -> {
//                    Intent(this, PreviewWrongActivity::class.java).also {
//                        startActivity(it)
//                        finish()
//                    }
//                }
//            }
//        }
//    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle("Batalkan Proses")
            setMessage("Apakah Anda Yakin Ingin Membatalkan Proses?")
            setPositiveButton("Ya") { _, _ ->
                Preferences.deleteImageCamera(this@PreviewActivity)
                Preferences.deleteImageGallery(this@PreviewActivity)
                super.onBackPressed()
            }
            setNegativeButton("Tidak") { _, _ -> }
            create()
            show()
        }
    }
}