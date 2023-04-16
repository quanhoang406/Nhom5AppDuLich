package com.nhom5.appdulich.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SingleToneModule {
//    @Provides
//    @Singleton
//    fun provideNotification(@ApplicationContext context: Context) : Notification = Notification(context)
}