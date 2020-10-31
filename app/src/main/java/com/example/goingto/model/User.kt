package com.example.goingto.model

import java.io.Serializable

data class User (
    val id: Int?,
    val email: String,
    val password: String,
    val wallet: Wallet?,
    val token: String?,
):Serializable
data class UserLogin(
    val email: String,
    val password: String,
):Serializable

data class UserResponse(
    val id:Int?,
    val email: String,
    val token: String,
):Serializable