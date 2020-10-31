package com.example.goingto.network

import com.example.goingto.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("api/Users")
    fun saveUser(@Body user: User): Call<User>

    @POST("api/Users/authenticate")
    fun authenticate(@Body user: User): Call<User>
}