package com.example.mvvmretrofitkotlin.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmretrofitkotlin.R
import com.example.mvvmretrofitkotlin.ui.viewmodel.UserViewModel
import com.example.mvvmretrofitkotlin.common.Result

class HomeActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel.users.observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    Log.d("check...", "loading")
                }

                is Result.Success -> {
                    Log.d("check...", "success: ${result.data.users}")
                }

                is Result.Failure -> {
                    Log.d("check...", "failure: ${result.message}")
                }
            }
        }
    }
}