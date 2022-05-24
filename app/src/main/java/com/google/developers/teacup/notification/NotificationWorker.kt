package com.google.developers.teacup.notification

import android.app.NotificationManager
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.developers.teacup.data.DataRepository

class NotificationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        val notificationManager = applicationContext
                .getSystemService(NotificationManager::class.java)
        val repository = DataRepository.getInstance(applicationContext)

        return Result.failure()
    }

    companion object {

        private val NOTIFICATION_ID = 22
        private val CHANNEL_ID = "notify-tea"
    }
}
