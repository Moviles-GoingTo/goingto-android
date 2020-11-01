package com.example.goingto.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ReferenceService {
    companion object{
        private const val URL_API= "https://api-goingto.azurewebsites.net/"
    }

    fun getClientService(): UserService {
        var retrofit = Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(UserService::class.java)
    }
}