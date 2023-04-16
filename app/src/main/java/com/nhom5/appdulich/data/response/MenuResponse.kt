package com.nhom5.appdulich.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.nhom5.appdulich.data.model.Menu

data class MenuResponse(
    @SerializedName("message") @Expose
    var message: String? = null,

    @SerializedName("statuscode")
    @Expose
    val statuscode: String? = null,

    @SerializedName("total")
    @Expose
    val total: Int? = null,

    @SerializedName("data")
    @Expose
    var data: List<Menu>? = null
)