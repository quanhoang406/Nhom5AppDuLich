package com.nhom5.appdulich.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Menu(
    @SerializedName("id") @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("icons")
    @Expose
    val icons: String? = null,

    @SerializedName("id_table")
    @Expose
    val idTable: Int? = null,

    @SerializedName("created_at")
    @Expose
    val createdAt: String? = null,

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
)