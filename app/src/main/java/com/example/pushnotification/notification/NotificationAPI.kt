package com.example.pushnotification.notification

import com.example.pushnotification.Constants.Companion.CONTENT_TYPE
import com.example.pushnotification.Constants.Companion.SERVER_KEY
import com.example.pushnotification.data.PushNotificationData
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface NotificationAPI {

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(@Body notification: PushNotificationData
    ): Response<ResponseBody>


}