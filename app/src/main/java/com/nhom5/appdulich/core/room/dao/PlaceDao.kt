package com.nhom5.appdulich.core.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nhom5.appdulich.core.room.FavoritePlace

@Dao
interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: FavoritePlace)

    @Query("select * from favorite_place")
    fun getAllPlace(): LiveData<List<FavoritePlace>>

    @Query("select * from favorite_place where name_place Like '%' || :namePlace || '%'")
    fun searchPlaceByName(namePlace: String): LiveData<List<FavoritePlace>>

    @Delete
    suspend fun delete(data: FavoritePlace)
}