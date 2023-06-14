package com.example.pasin_app.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class HistoryDashboardResponse(

	@field:SerializedName("HistoryDashboardResponse")
	val historyDashboardResponse: List<HistoryDashboardResponseItem?>? = null
) : Parcelable

@Parcelize
data class HistoryDashboardResponseItem(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("umur")
	val umur: Int? = null,

	@field:SerializedName("shoulders_width")
	val shouldersWidth: Int? = null,

	@field:SerializedName("hip_width")
	val hipWidth: Int? = null,

	@field:SerializedName("detail_id")
	val detailId: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
) : Parcelable
