package com.example.mvvmretrofitkotlin.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

     private const val BASE_URL = "https://dummyjson.com/"

     private val retrofit = Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build()

     val apiService: ApiService by lazy {
          retrofit.create(ApiService::class.java)
     }
}