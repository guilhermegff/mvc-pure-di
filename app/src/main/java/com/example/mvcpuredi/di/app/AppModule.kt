package com.example.mvcpuredi.di.app

import android.app.Application
import com.example.mvcpuredi.di.qualifiers.RetrofitOne
import com.example.mvcpuredi.di.qualifiers.RetrofitTwo
import com.example.mvcpuredi.networking.UrlProvider
import com.example.mvcpuredi.networking.UsersApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private val application: Application) {

    @Provides
    @AppScope
    fun urlProvider() = UrlProvider()

    @Provides
    @AppScope
    @RetrofitOne
    fun retrofitOne(urlProvider: UrlProvider): Retrofit {
        return Retrofit.Builder().baseUrl(urlProvider.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }).build()).build()
    }

    @Provides
    @AppScope
    @RetrofitTwo
    fun retrofitTwo(urlProvider: UrlProvider): Retrofit {
        return Retrofit.Builder().baseUrl(urlProvider.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }).build()).build()
    }

    @Provides
    fun application() = application

    @Provides
    @AppScope
    fun usersApi(@RetrofitOne retrofit: Retrofit) = retrofit.create(UsersApi::class.java)
}