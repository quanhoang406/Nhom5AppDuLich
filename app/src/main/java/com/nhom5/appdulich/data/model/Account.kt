package com.nhom5.appdulich.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("email")
    @Expose
    var email: String? = null,

    @SerializedName("phone")
    @Expose
    var phone: String? = null,

    @SerializedName("password")
    @Expose
    var password: String? = null,

    @SerializedName("token")
    @Expose
    var token: String? = null,

    @SerializedName("avatar")
    @Expose
    var avatar: String? = null,

    @SerializedName("age")
    @Expose
    var age: String? = null,

    @SerializedName("gender")
    @Expose
    var gender: String? = null,

    @SerializedName("id_hierarchy")
    @Expose
    var idHierarchy: Int? = null,

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null,

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
)