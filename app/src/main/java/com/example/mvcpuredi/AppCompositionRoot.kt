package com.example.mvcpuredi

import android.app.Application
import androidx.annotation.UiThread
import com.example.mvcpuredi.networking.UsersApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@UiThread
class AppCompositionRoot(val application: Application) {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl("http://10.0.2.2:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }).build()).build()
    }

    val usersApi: UsersApi by lazy {
        retrofit.create(UsersApi::class.java)
    }
}