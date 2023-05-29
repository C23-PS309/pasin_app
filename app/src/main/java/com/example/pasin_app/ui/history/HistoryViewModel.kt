package com.example.pasin_app.ui.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pasin_app.model.History
import com.example.pasin_app.repository.ItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HistoryViewModel(private val repository: ItemRepository) : ViewModel() {
    private val _groupedItems = MutableStateFlow(
        repository.getItem()
            .sortedBy { it.title }
            .groupBy { it.title[0] }
    )

    val groupedItems: StateFlow<Map<Char, List<History>>> get() = _groupedItems

}