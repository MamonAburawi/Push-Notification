package com.example.pushnotification.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavDeepLinkBuilder
import com.example.pushnotification.Constants.Companion.CHANNEL_ID
import com.example.pushnotification.MainActivity
import com.example.pushnotification.RetrofitInstance
import com.example.pushnotification.data.NotificationData
import com.example.pushnotification.data.PushNotificationData
import com.example.pusnothification.R
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.prefs.Preferences
import kotlin.random.Random

private var token = ""

fun NotificationManager.createNotification(message:RemoteMessage , applicationContext: Context , destination: Int){

    val intent = Intent(applicationContext, MainActivity::class.java) // here we set the intent you can use link or any thing ..
    val notificationManager = getSystemService(applicationContext,NotificationManager::class.java) as NotificationManager
    val notificationID = Random.nextInt()

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        createNotificationChannel(notificationManager)
    }

    val pendingIntent = NavDeepLinkBuilder(applicationContext)
        .setComponentName(MainActivity::class.java)
        .setGraph(R.navigation.nav_graph)
        .setDestination(destination)
        .createPendingIntent()

    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//    val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_ONE_SHOT)
    val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        .setContentTitle(message.data["title"])
        .setContentText(message.data["body"])
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setAutoCancel(true)
        .setContentIntent(pendingIntent) // this code when click on notification..
        .build()

    notificationManager.notify(notificationID, notification)
}

@RequiresApi(Build.VERSION_CODES.O)
private fun createNotificationChannel(notificationManager: NotificationManager) {
    val channelName = "channelName"
    val channel = NotificationChannel(CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_HIGH).apply {
        description = "My channel description"
        enableLights(true)
        lightColor = Color.GREEN
    }
    notificationManager.createNotificationChannel(channel) // create notification channel ..
}

fun sendNotification(notification: PushNotificationData , TAG: String) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if(response.isSuccessful) {
                Log.d(TAG, "Response: ${Gson().toJson(response)}")
            } else {
                Log.e(TAG, response.errorBody().toString())
            }
        } catch(e: Exception) {
            Log.e(TAG, e.toString())
        }
    }
}

fun notifyData(title: String , bodyMessage:String ,to:String, destination: Int) : PushNotificationData {
    return PushNotificationData( NotificationData(title,bodyMessage,destination),to)
}

fun subscribeToTopic(topic: String){
    FirebaseMessaging.getInstance().subscribeToTopic(topic)
}

