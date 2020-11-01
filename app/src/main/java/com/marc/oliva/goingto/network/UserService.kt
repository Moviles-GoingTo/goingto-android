package com.example.goingto.network

import com.example.goingto.model.User
import com.example.goingto.model.UserLogin
import com.example.goingto.model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("api/Users")
    fun saveUser(@Body user: User): Call<User>

    @POST("api/Users/authenticate")
    fun authenticate(@Body user:UserLogin): Call<UserResponse>
}