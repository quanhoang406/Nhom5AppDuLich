package com.nhom5.appdulich.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.nhom5.appdulich.data.model.Evaluate
import com.nhom5.appdulich.data.model.Place

data class DetailResponse(
    @SerializedName("message") @Expose
    var message: String? = null,

    @SerializedName("statuscode")
    @Expose
    val statuscode: String? = null,

    @SerializedName("totalPlace")
    @Expose
    val totalPlace: Int? = null,

    @SerializedName("totalEvaluate")
    @Expose
    val totalEvaluate: Int? = null,

    @SerializedName("data")
    @Expose
    val data: List<Place>? = null,

    @SerializedName("dataEvaluate")
    @Expose
    val dataEvaluate: List<Evaluate>? = null
)