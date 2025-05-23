package com.example.mvvmretrofitkotlin.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmretrofitkotlin.R
import com.example.mvvmretrofitkotlin.common.Result
import com.example.mvvmretrofitkotlin.common.hide
import com.example.mvvmretrofitkotlin.common.letEmpty
import com.example.mvvmretrofitkotlin.common.show
import com.example.mvvmretrofitkotlin.common.showSnackBar
import com.example.mvvmretrofitkotlin.common.showToast
import com.example.mvvmretrofitkotlin.data.api.RetrofitClient
import com.example.mvvmretrofitkotlin.data.model.User
import com.example.mvvmretrofitkotlin.databinding.ActivityHomeBinding
import com.example.mvvmretrofitkotlin.ui.adapter.UserAdapter
import com.example.mvvmretrofitkotlin.ui.fragment.BottomSheetFragment
import com.example.mvvmretrofitkotlin.ui.viewmodel.UserViewModel
import com.example.mvvmretrofitkotlin.ui.viewmodel.UserViewModelFactory

class HomeActivity : AppCompatActivity() {

     private lateinit var binding: ActivityHomeBinding

     private val viewModel: UserViewModel by viewModels {
          UserViewModelFactory(RetrofitClient.apiService)
     }

     private val userAdapter by lazy {
          UserAdapter { user ->
               showBottomSheet(user)
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

     private fun showError(message: String) {
          binding.progressBar.hide()
          binding.recyclerView.hide()
          binding.root.showSnackBar(message)
     }

     private fun displayData(users: List<User>?) {
          binding.progressBar.hide()
          binding.recyclerView.show()
          users?.letEmpty {
               userAdapter.submitList(users)
          } ?: showToast(getString(R.string.empty_data))
     }

     private fun showLoadingIndicator() {
          binding.progressBar.show()
          binding.recyclerView.hide()
     }

     private fun setupRecyclerView() {
          binding.recyclerView.apply {
               layoutManager = LinearLayoutManager(this@HomeActivity)
               adapter = userAdapter
          }
     }

     private fun showBottomSheet(user: User) {
          val bottomSheetFragment = BottomSheetFragment.newInstance(user)
          bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
     }
}