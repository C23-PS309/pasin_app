package com.example.pasin_app.repository

import com.example.pasin_app.model.History
import com.example.pasin_app.model.HistoryData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ItemRepository {
    fun getItem(): List<History> {
        return HistoryData.historyData
    }
    fun getItemById(id: String): History? {
        return HistoryData.historyData.find { it.historyID == id }
    }
    fun searchItem(query: String): List<History>{
        return HistoryData.historyData.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }

    companion object {
        @Volatile
        private var instance: ItemRepository? = null

        fun getInstance(): ItemRepository =
            instance ?: synchronized(this) {
                ItemRepository().apply {
                    instance = this
                }
            }
    }
}