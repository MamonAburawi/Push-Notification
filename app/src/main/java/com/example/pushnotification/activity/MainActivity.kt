package com.example.pushnotification.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pushnotification.RetrofitInstance
import com.example.pushnotification.data.NotificationData
import com.example.pushnotification.data.PushNotificationData
import com.example.pushnotification.service.FirebaseService
import com.example.pusnothification.R
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


const val TOPIC = "topic" // This variable shares the notification to all devices that contain the same topic ..


class MainActivity : AppCompatActivity() {

    companion object{
        val FILE_NAME_KEY = "fileName"
    }

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        FirebaseService.SharedPref = getSharedPreferences(FILE_NAME_KEY, MODE_PRIVATE) // this code use it if you need to storage some values ..

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            FirebaseService.token = it.token   // this code for get the token of the device and set it in the the token value inside the token variable ..
            EditText_Token.setText(it.token)
        }

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC) // the code for send notification for all devices that have the same topic variable ..

        // Button_Send ..
        Button_Send.setOnClickListener {
            val Title = EditText_Title.text.toString()
            val Message = EditText_Message.text.toString()
            val Token = EditText_Token.text.toString()
            if(Title.isNotEmpty() && Message.isNotEmpty() && Token.isNotEmpty()) {
                PushNotificationData(
                    NotificationData(Title, Message), // Data  parameter..
                    Token // to parameter..    // --> you can use the topic for send the notification to specific multi devices or use the token to send the notification to single device ..
                ).also {
                    sendNotification(it)
                }
            }
        }




    }



    private fun sendNotification(notification: PushNotificationData) = CoroutineScope(Dispatchers.IO).launch {
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
