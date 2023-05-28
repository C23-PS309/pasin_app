package com.example.pasin_app.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pasin_app.R
import com.example.pasin_app.databinding.ActivityHistoryBinding
import com.example.pasin_app.model.History

class HistoryActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHistoryBinding.inflate(layoutInflater) }
    private val list = ArrayList<History>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvHistories.layoutManager = GridLayoutManager(this, 2)
        list.addAll(dummyListHistory())
        binding.rvHistories.adapter = ListHistoryAdapter(list)

    }

    private fun dummyListHistory(): ArrayList<History>{
        val dataTitle = arrayOf(
            "Nama 1",
            "Nama 2",
            "Nama 3",
            "Nama 4",
            "Nama 5"
        )
        val dataDesc = arrayOf(
            "Desc 1",
            "Desc 2",
            "Desc 3",
            "Desc 4",
            "Desc 5"
        )

        val listHistory = ArrayList<History>()
        for (i in dataTitle.indices) {
            val article = History(dataTitle[i], dataDesc[i], R.drawable.contoh_foto)
            listHistory.add(article)
        }
        return listHistory
    }
}