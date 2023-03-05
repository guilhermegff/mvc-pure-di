package com.example.mvcpuredi.networking

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApi {
    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<UserResponse>
}