package com.example.mvcpuredi.usecases

import com.example.mvcpuredi.networking.UsersApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class FetchUserDetailUseCase(private val retrofit: Retrofit) {
    sealed class Result {
        class Success(val userId: Int) : Result()
        object Failure : Result()
    }

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