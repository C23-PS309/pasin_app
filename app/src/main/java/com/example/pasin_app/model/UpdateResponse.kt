package com.example.pasin_app.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class UpdateResponse(

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable
