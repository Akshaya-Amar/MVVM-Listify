package com.example.mvvmretrofitkotlin.data.repository

import com.example.mvvmretrofitkotlin.common.Result
import com.example.mvvmretrofitkotlin.data.model.UserData

interface UserRepository {
     suspend fun getUsers(): Result<UserData>
}