package com.nhom5.appdulich.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("id") @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("image")
    @Expose
    val image: String? = null,

    @SerializedName("updated_at")
    @Expose
    val updated_at: String? = null
)