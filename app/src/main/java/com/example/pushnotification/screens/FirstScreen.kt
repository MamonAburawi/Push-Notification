package com.example.pushnotification.screens

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.pushnotification.data.NotificationData
import com.example.pushnotification.data.PushNotificationData
import com.example.pushnotification.notification.createNotification
import com.example.pushnotification.notification.notifyData
import com.example.pushnotification.notification.sendNotification
import com.example.pushnotification.service.FirebaseService
import com.example.pusnothification.R
import com.example.pusnothification.databinding.FirstScreenBinding
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.RemoteMessage


class FirstScreen: Fragment() {


    private lateinit var binding : FirstScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.first_screen,container,false)




        binding.apply {

            // for get the token ..
            FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
                val token = it.token
                EditTextToken.setText(token)
            }



            // Button_Send ..
            ButtonSend.setOnClickListener {
                val title = EditTextTitle.text.toString()
                val bodyMessage = EditTextMessage.text.toString()
                val token = EditTextToken.text.toString()
                if(title.isNotEmpty() && bodyMessage.isNotEmpty() && token.isNotEmpty()) {
                    val destination = R.id.secondScreen // this for destination to second screen after click on notification
                    sendNotification(notifyData(title,bodyMessage,token,destination), "FirstScreen")  // --> you can use the topic for send the notification to specific multi devices or use the token to send the notification to single device ..
                }else{
                    Toast.makeText(activity,"Please fill all information", Toast.LENGTH_SHORT).show()
                }
            }

        }


        /** this code for send notification for all devices that have the same topic variable **/
//      subscribeToTopic(TOPIC)




        return binding.root
    }








}