package com.nhom5.appdulich.core.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nhom5.appdulich.core.room.FavoritePlace
import com.nhom5.appdulich.core.room.dao.PlaceDao

@Database(entities = [FavoritePlace::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getFavoritePlaceDao(): PlaceDao
}