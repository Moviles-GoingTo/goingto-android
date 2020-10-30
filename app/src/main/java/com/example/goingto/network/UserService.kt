package com.example.goingto.network

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {
    @POST("/api/Users")
    @FormUrlEncoded
    fun saveUser(@Field("email") email: String,
                @Field("password") password: String)
}