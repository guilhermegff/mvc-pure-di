package com.example.mvcpuredi.usecases

import com.example.mvcpuredi.networking.UsersApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchUserDetailUseCase(private val usersApi: UsersApi) {
    sealed class Result {
        data class Success(val userId: Int) : Result()
        object Failure : Result()
    }

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