package com.example.pushnotification

import com.example.pushnotification.Constants.Companion.BASE_URL
import com.example.pushnotification.notification.NotificationAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private val retrofit by lazy { Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api by lazy { retrofit.create(NotificationAPI::class.java) }


    }
}