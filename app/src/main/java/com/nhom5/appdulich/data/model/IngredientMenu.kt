package com.nhom5.appdulich.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class IngredientMenu(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("id_menu")
    @Expose
    var idMenu: Int? = null,

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null,

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
)