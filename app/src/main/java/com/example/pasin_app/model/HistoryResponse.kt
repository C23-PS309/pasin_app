package com.example.pasin_app.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class HistoryResponse(

	@field:SerializedName("HistoryResponse")
	val historyResponse: List<HistoryResponseItem>
) : Parcelable

@Parcelize
data class HistoryResponseItem(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("umur")
	val umur: Float? = null,

	@field:SerializedName("shoulders_width")
	val shouldersWidth: Float? = null,

	@field:SerializedName("hip_width")
	val hipWidth: Float? = null,

	@field:SerializedName("hip_width")
	val link_gambar: String? = null,

	@field:SerializedName("detail_id")
	val detailId: String? = null,

	@field:SerializedName("height")
	val height: Float? = null
) : Parcelable
