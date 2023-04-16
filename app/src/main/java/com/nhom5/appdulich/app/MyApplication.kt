package com.nhom5.appdulich.app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.multidex.MultiDexApplication
import com.nhom5.appdulich.R
import com.nhom5.appdulich.utils.Const
import com.nhom5.appdulich.utils.Helpers
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : MultiDexApplication() {
    @Inject
    lateinit var helpers: Helpers

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                Const.CHANEL_ID,
                getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
                notificationChannel
            )
        }
        helpers.showNotification(getString(R.string.lbl_hello_app))
    }
}