package com.example.mvvmretrofitkotlin.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofitkotlin.data.api.ApiService
import com.example.mvvmretrofitkotlin.data.repository.UserRepositoryImpl

class UserViewModelFactory(
     private val apiService: ApiService
) : ViewModelProvider.Factory {
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
          if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
               val repo = UserRepositoryImpl(apiService)
               return UserViewModel(repo) as T
          }
          return super.create(modelClass)
     }
}