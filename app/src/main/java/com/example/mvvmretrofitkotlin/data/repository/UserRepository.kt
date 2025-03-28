package com.example.mvvmretrofitkotlin.data.repository

import com.example.mvvmretrofitkotlin.data.api.ApiService
import com.example.mvvmretrofitkotlin.data.api.RetrofitClient
import com.example.mvvmretrofitkotlin.data.model.UserData
import com.example.mvvmretrofitkotlin.common.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(
    private val apiService: ApiService = RetrofitClient.apiService
) {
    suspend fun fetchUsers(): Result<UserData> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getUsers()
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

/*which way is better
1st to return the data as Result<>
suspend fun fetchUsers(): Result<UserData> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getUsers()
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    return@withContext Result.Success(data)
                } ?: run {
                    return@withContext Result.Failure(Exception(response.message()))
                }
            } else {
                return@withContext Result.Failure(Exception("Not successful: ${response.code()}, ${response.message()}"))
            }
        } catch (exception: Exception) {
            return@withContext Result.Failure(exception)
        }
    }

or
2nd one to handle using success and failure callbacks in arguments?

which is better and recommended?

ChatGPT said:
​*/