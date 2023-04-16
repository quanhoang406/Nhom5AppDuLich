package com.nhom5.appdulich.di

import android.app.Application
import androidx.room.Room
import com.nhom5.appdulich.core.room.dao.PlaceDao
import com.nhom5.appdulich.core.room.database.AppDatabase
import com.nhom5.appdulich.utils.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(
        application: Application,
    ): AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, Const.APP_DATABASE)
            .build()

    @Provides
    @Singleton
    fun providePlaceDao(appDatabase: AppDatabase): PlaceDao {
        return appDatabase.getFavoritePlaceDao()
    }
}