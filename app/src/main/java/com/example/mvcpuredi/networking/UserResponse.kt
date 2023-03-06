package com.example.mvcpuredi.networking

import com.example.mvcpuredi.User

data class UserResponse(
    val id: Int,
    val name: String,
    val status: String,
)

fun UserResponse.toUser() = User(
    id, name, status
)