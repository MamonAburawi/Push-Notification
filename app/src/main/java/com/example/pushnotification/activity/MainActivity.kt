package com.example.pushnotification.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pushnotification.data.NotificationData
import com.example.pushnotification.data.PushNotificationData
import com.example.pushnotification.notification.sendNotification
import com.example.pushnotification.notification.subscribeToTopic
import com.example.pusnothification.R
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*


const val TOPIC = "topic" // This variable shares the notification to all devices that contain the same topic ..
const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // for get the token ..
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            val token = it.token
            EditText_Token.setText(token)
        }


        /** this code for send notification for all devices that have the same topic variable **/
        //  subscribeToTopic(TOPIC)


        // Button_Send ..
        Button_Send.setOnClickListener {
            val title = EditText_Title.text.toString()
            val bodyMessage = EditText_Message.text.toString()
            val token = EditText_Token.text.toString()
            if(title.isNotEmpty() && bodyMessage.isNotEmpty() && token.isNotEmpty()) {
                sendNotification(notifyData(title,bodyMessage,token),TAG)  // --> you can use the topic for send the notification to specific multi devices or use the token to send the notification to single device ..
            }else{
                Toast.makeText(this,"Please fill all information",Toast.LENGTH_SHORT).show()
            }
        }


    }


    private fun notifyData(title: String , bodyMessage:String ,to:String) : PushNotificationData{
        return PushNotificationData( NotificationData(title,bodyMessage),to)
    }



}
