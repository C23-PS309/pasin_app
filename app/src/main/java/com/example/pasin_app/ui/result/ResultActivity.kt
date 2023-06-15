package com.example.pasin_app.ui.result

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.pasin_app.R
import com.example.pasin_app.custom_view.ResultEditText
import com.example.pasin_app.databinding.ActivityResultBinding
import com.example.pasin_app.model.UserPreference
import com.example.pasin_app.ui.detail.DetailActivity
import com.example.pasin_app.ui.history.HistoryActivity
import com.example.pasin_app.ui.home.MainActivity
import com.example.pasin_app.utils.ViewModelFactory
import com.example.pasin_app.utils.sharedPreferences

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class ResultActivity : AppCompatActivity() {

    private val binding by lazy { ActivityResultBinding.inflate(layoutInflater) }
    private lateinit var resultViewModel: ResultViewModel
    private lateinit var resultEditText: ResultEditText
    private var genderState: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val selectedImage = sharedPreferences.getImageGallery(this)
        binding.ivPreviewImage.setImageURI(Uri.parse(selectedImage))
        Log.d("URI", "onCreate: $selectedImage")
        resultEditText = binding.resultEdit

        binding.tvValueTinggi.text = sharedPreferences.getTinggi(this)
        binding.tvValueUmur.text = sharedPreferences.getUmur(this)

        setupView()

        resultViewModel.getUser().observe(this){
            val token = it.token
            val id = intent.getStringExtra(EXTRA_ID).toString()
            resultViewModel.getResult(id, "Bearer $token")
        }

        genderState = true

        if (genderState) {
            binding.apply {
                cardView.backgroundTintList = resources.getColorStateList(R.color.blue_gender)
                imageViewGender.background = resources.getDrawable(R.drawable.male)
            }
        } else {
            binding.apply {
                cardView.backgroundTintList = resources.getColorStateList(R.color.pink)
                imageViewGender.background = resources.getDrawable(R.drawable.gender_female)
            }
        }

        binding.btnHome.setOnClickListener {
            val name = binding.resultEdit.text.toString()
            val umur = binding.tvValueUmur.text.toString()
            val pinggul = binding.tvValuePinggul.text.toString()
            val bahu = binding.tvValueBahu.text.toString()
            val tinggi = binding.tvValueTinggi.text.toString()

            sharedPreferences.saveName(name, this)
            sharedPreferences.saveUmur(umur, this)
            sharedPreferences.savePinggul(pinggul, this)
            sharedPreferences.saveBahu(bahu, this)
            sharedPreferences.saveTinggi(tinggi, this)

            Intent(this, MainActivity::class.java).apply {
                putExtra(MainActivity.EXTRA_URI, selectedImage.toString())
                Log.d("URI Select", "onCreate: $selectedImage")
                startActivity(this)
                finish()
            }

            if (resultEditText.hasInput()) {
                val alertDialogBuilder = AlertDialog.Builder(this)
                    .setTitle("Judul Pengukuran")
                    .setMessage("Apakah Anda ingin menyimpan perubahan judul pengukuran?")
                    .setPositiveButton("Simpan") { dialog, _ ->
//                        resultViewModel.saveTitle(
//                            intent.getStringExtra(EXTRA_ID).toString(),
//                            "Bearer ${resultViewModel.getUser().value?.token}",
//                            binding.resultEdit.text.toString()
//                        )
                        dialog.dismiss()
                        Intent(this, MainActivity::class.java).also {
                            startActivity(it)
                            finish()
                        }
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }
                alertDialogBuilder.create().show()
            } else {
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }

        binding.btnHistory.setOnClickListener {
            if (resultEditText.hasInput()) {
                val alertDialogBuilder = AlertDialog.Builder(this)
                    .setTitle("Judul Pengukuran")
                    .setMessage("Apakah Anda ingin menyimpan perubahan judul pengukuran?")
                    .setPositiveButton("Simpan") { dialog, _ ->
//                        resultViewModel.saveTitle(
//                            intent.getStringExtra(EXTRA_ID).toString(),
//                            "Bearer ${resultViewModel.getUser().value?.token}",
//                            binding.resultEdit.text.toString()
//                        )
                        dialog.dismiss()
                        Intent(this, HistoryActivity::class.java).also {
                            startActivity(it)
                            finish()
                        }
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }
                alertDialogBuilder.create().show()
            } else {
                Intent(this, HistoryActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }

    private fun setupView() {
        resultViewModel = ViewModelProvider(
            this,
            ViewModelFactory(context = this, pref = UserPreference.getInstance(dataStore))
        )[ResultViewModel::class.java]
    }

    override fun onBackPressed() {
        if (resultEditText.hasInput()) {
            val alertDialogBuilder = AlertDialog.Builder(this)
                .setTitle("Judul Pengukuran")
                .setMessage("Apakah Anda ingin menyimpan perubahan judul pengukuran?")
                .setPositiveButton("Simpan") { dialog, _ ->
//                    resultViewModel.saveTitle(
//                        intent.getStringExtra(EXTRA_ID).toString(),
//                        "Bearer ${resultViewModel.getUser().value?.token}",
//                        binding.resultEdit.text.toString()
//                    )
                    dialog.dismiss()
                    super.onBackPressed()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
            alertDialogBuilder.create().show()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        const val EXTRA_ID = "123"
    }
}
