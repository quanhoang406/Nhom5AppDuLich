package com.nhom5.appdulich.core.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_place")
data class FavoritePlace(
    @PrimaryKey
    @ColumnInfo(name = "id_place")
    var id: Int,

    @ColumnInfo(name = "name_place")
    var title: String? = null,

    @ColumnInfo(name = "introduce_place")
    var introduce: String ? = null,

    @ColumnInfo(name = "image_place")
    var image: String? = null
)