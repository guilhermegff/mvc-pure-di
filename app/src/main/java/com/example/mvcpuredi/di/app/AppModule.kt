package com.example.mvcpuredi.di.app

import android.app.Application
import com.example.mvcpuredi.networking.UsersApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(val application: Application) {
    @Provides
    @AppScope
    fun retrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://10.0.2.2:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }).build()).build()
    }

    @Provides
    fun application() = application

    @Provides
    @AppScope
    fun usersApi(retrofit: Retrofit) = retrofit.create(UsersApi::class.java)
}