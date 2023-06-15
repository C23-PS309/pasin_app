package com.example.pasin_app.model

data class History(
    val historyID: String,
    val title: String,
    val photo: Int,
    val age: Int,
    val recommendation: String,
    val measureData: Measure,
)