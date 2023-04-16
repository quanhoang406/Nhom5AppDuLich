package com.nhom5.appdulich.utils

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import com.nhom5.appdulich.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Notification @Inject constructor(@ApplicationContext private val context: Context) {
    fun createNotification(content: String): Notification {
        val notification = NotificationCompat.Builder(context, Const.CHANEL_ID).apply {
            setContentTitle(context.getString(R.string.app_name))
            setContentText(content)
            setSmallIcon(R.drawable.ic_launcher_background)
            setTimeoutAfter(10000)
            build()
        }
        return notification.build()
    }
}