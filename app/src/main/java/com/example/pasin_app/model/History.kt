package com.example.pasin_app.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class History(
    var title: String,
    var description: String,
    var photo: Int
) : Parcelable