package com.example.pushnotification.service


import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.pushnotification.data.NotificationData
import com.example.pushnotification.notification.createNotification
import com.example.pushnotification.screens.SecondScreen
import com.example.pusnothification.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class FirebaseService() : FirebaseMessagingService() {

    init {
        Log.i("Notification","new notification")
    }

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val notificationManager = ContextCompat
            .getSystemService(applicationContext, NotificationManager::class.java) as NotificationManager

        message.let {
            val title = it.data["title"]
            val body = it.data["body"]
            val destination = it.data["destination"]!!.toInt()

            notificationManager.createNotification(message,
                applicationContext,destination)


        }




    }
}