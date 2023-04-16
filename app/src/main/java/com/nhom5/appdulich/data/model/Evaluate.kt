package com.nhom5.appdulich.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Evaluate(
    @SerializedName("id") @Expose
    var id: Int? = null,

    @SerializedName("id_user")
    @Expose
    val idUser: Int? = null,

    @SerializedName("id_place")
    @Expose
    val idPlace: Int? = null,

    @SerializedName("comment")
    @Expose
    val comment: String? = null,

    @SerializedName("rating")
    @Expose
    val rating: Int? = null,

    @SerializedName("like")
    @Expose
    val like: Int? = null,

    @SerializedName("created_at")
    @Expose
    val createdAt: String? = null,

    @SerializedName("updated_at")
    @Expose
    val updatedAt: String? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("avatar")
    @Expose
    val avatar: String? = null
)