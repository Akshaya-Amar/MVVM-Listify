package com.example.mvvmretrofitkotlin.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmretrofitkotlin.common.Result
import com.example.mvvmretrofitkotlin.common.hide
import com.example.mvvmretrofitkotlin.common.show
import com.example.mvvmretrofitkotlin.common.showToast
import com.example.mvvmretrofitkotlin.data.model.User
import com.example.mvvmretrofitkotlin.databinding.ActivityHomeBinding
import com.example.mvvmretrofitkotlin.ui.adapter.UserAdapter
import com.example.mvvmretrofitkotlin.ui.viewmodel.UserViewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding

    private val userAdapter by lazy {
        UserAdapter { user ->
            showToast(user.firstName!!)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        viewModel.users.observe(this) { result ->
            when (result) {
                is Result.Loading -> showLoadingIndicator()
                is Result.Success -> displayData(result.data.users)
                is Result.Failure -> showError(result.message)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = userAdapter
        }
    }

    private fun showError(message: String) {
        with(binding) {
            progressBar.hide()
            recyclerView.show()
        }
        showToast(message)
    }

    private fun displayData(users: List<User>?) {
        with(binding) {
            progressBar.hide()
            recyclerView.show()
        }
        userAdapter.submitList(users)
    }

    private fun showLoadingIndicator() {
        with(binding) {
            progressBar.show()
            recyclerView.hide()
        }
    }
}