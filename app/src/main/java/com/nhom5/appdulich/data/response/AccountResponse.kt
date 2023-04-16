package com.nhom5.appdulich.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.nhom5.appdulich.data.model.Account

data class AccountResponse(
    @SerializedName("message")
    @Expose
    val message: String? = null,

    @SerializedName("statuscode")
    @Expose
    val statuscode: Int? = null,

    @SerializedName("total")
    @Expose
    val total: Int? = null,

    @SerializedName("data")
    @Expose
    val data: List<Account>? = null
)