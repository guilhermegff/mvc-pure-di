package com.example.mvcpuredi

import com.example.mvcpuredi.networking.UsersApi
import com.example.mvcpuredi.usecases.FetchUserDetailUseCase
import com.example.mvcpuredi.usecases.FetchUsersUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppCompositionRoot {
    private val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }).build()).build()

    val usersApi: UsersApi = retrofit.create(UsersApi::class.java)

    val fetchUsersUseCase get() = FetchUsersUseCase(usersApi)

    val fetchUserDetailUseCase get() = FetchUserDetailUseCase(usersApi)
}