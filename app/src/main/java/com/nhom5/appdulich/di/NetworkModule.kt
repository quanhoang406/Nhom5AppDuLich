package com.nhom5.appdulich.di

import com.nhom5.appdulich.core.room.database.AppDatabase
import com.nhom5.appdulich.core.service.ApiServices
import com.nhom5.appdulich.repositories.AccountRepository
import com.nhom5.appdulich.repositories.MenuRepository
import com.nhom5.appdulich.repositories.PlaceRepository
import com.nhom5.appdulich.utils.Const
import com.nhom5.appdulich.utils.SharePrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

    @Provides
    @Singleton
    fun provideAccountRepository(
        apiServices: ApiServices,
        sharePrefs: SharePrefs
    ): AccountRepository = AccountRepository(apiServices, sharePrefs)

    @Provides
    @Singleton
    fun providePlaceRepository(apiServices: ApiServices, appDatabase: AppDatabase) =
        PlaceRepository(apiServices, appDatabase.getFavoritePlaceDao())

    @Provides
    @Singleton
    fun provideMenuRepository(apiServices: ApiServices) = MenuRepository(apiServices)
}