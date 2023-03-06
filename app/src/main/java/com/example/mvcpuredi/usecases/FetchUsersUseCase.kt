package com.example.mvcpuredi.usecases

import com.example.mvcpuredi.User
import com.example.mvcpuredi.networking.UsersApi
import com.example.mvcpuredi.networking.toUser
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class FetchUsersUseCase(private val retrofit: Retrofit) {
    sealed class Result {
        class Success(val users: List<User>) : Result()
        object Failure : Result()
    }

    private val usersApi: UsersApi = retrofit.create(UsersApi::class.java)

    suspend fun fetchUsers(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = usersApi.getUsers()
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!.map {
                        it.toUser()
                    })
                } else {
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }
        }
    }
}