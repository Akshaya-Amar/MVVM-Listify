package com.example.mvvmretrofitkotlin.data.repository

import com.example.mvvmretrofitkotlin.common.Result
import com.example.mvvmretrofitkotlin.data.api.ApiService
import com.example.mvvmretrofitkotlin.data.model.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
     private val apiService: ApiService
) : UserRepository {
     override suspend fun getUsers(): Result<UserData> = withContext(Dispatchers.IO) {
          try {
               val response = apiService.fetchUsers()
               if (response.isSuccessful) {
                    response.body()?.let { data ->
                         Result.Success(data)
                    } ?: Result.Failure("Data is empty")
               } else {
                    Result.Failure("Not successful: ${response.code()}, ${response.message()}")
               }
          } catch (exception: Exception) {
               Result.Failure(exception.message.toString())
          }
     }
}