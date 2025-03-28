package com.example.mvvmretrofitkotlin.data.api

import com.example.mvvmretrofitkotlin.data.model.UserData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<UserData>
}