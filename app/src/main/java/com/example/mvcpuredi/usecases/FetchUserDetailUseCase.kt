package com.example.mvcpuredi.usecases

import com.example.mvcpuredi.User
import com.example.mvcpuredi.networking.UsersApi
import com.example.mvcpuredi.networking.toUser
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FetchUserDetailUseCase {
    sealed class Result {
        class Success(val userId: Int) : Result()
        object Failure : Result()
    }

    private val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }).build()).build()

    private val usersApi: UsersApi = retrofit.create(UsersApi::class.java)

    suspend fun fetchUsers(id: Int): FetchUserDetailUseCase.Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = usersApi.getUser(id)
                if (response.isSuccessful && response.body() != null) {
                    return@withContext FetchUserDetailUseCase.Result.Success(response.body()!!.id)
                } else {
                    return@withContext FetchUserDetailUseCase.Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext FetchUserDetailUseCase.Result.Failure
                } else {
                    throw t
                }
            }
        }
    }
}