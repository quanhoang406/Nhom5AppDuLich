package com.nhom5.appdulich.core.room

import androidx.room.*

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data : T)

    @Delete
    suspend fun delete(data : T)
}