package com.example.mvvmretrofitkotlin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmretrofitkotlin.data.model.UserData
import com.example.mvvmretrofitkotlin.data.repository.UserRepository
import com.example.mvvmretrofitkotlin.common.Result
import com.example.mvvmretrofitkotlin.data.repository.UserRepositoryImpl
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository = UserRepositoryImpl()
) : ViewModel() {

    private var _users = MutableLiveData<Result<UserData>>(Result.Loading)
    val users: LiveData<Result<UserData>> get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            val data = userRepository.fetchUsers()
            _users.postValue(data)
        }
    }
}