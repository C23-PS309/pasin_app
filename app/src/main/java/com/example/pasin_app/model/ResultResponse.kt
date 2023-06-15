package com.example.pasin_app.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResultResponse(

	@field:SerializedName("ResultResponse")
	val resultResponse: List<ResultResponseItem>
) : Parcelable

@Parcelize
data class ResultResponseItem(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("nama_history")
	val namaHistory: String? = null,

	@field:SerializedName("umur")
	val umur: Int? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("tanggal_pengukuran")
	val tanggalPengukuran: String? = null,

	@field:SerializedName("link_gambar")
	val linkGambar: String? = null,

	@field:SerializedName("shoulders_width")
	val shouldersWidth: Int? = null,

	@field:SerializedName("hip_width")
	val hipWidth: Int? = null,

	@field:SerializedName("detail_id")
	val detailId: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
) : Parcelable
