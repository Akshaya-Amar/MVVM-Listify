package com.example.mvvmretrofitkotlin.ui.fragment

import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.mvvmretrofitkotlin.R
import com.example.mvvmretrofitkotlin.data.model.User
import com.example.mvvmretrofitkotlin.databinding.BottomSheetItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

     private lateinit var binding: BottomSheetItemBinding

     override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
     ): View {
          binding = BottomSheetItemBinding.inflate(inflater, container, false)
          return binding.root
     }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          super.onViewCreated(view, savedInstanceState)

          val userData: User? = if (VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
               arguments?.getParcelable(USER_KEY, User::class.java)
          } else {
               arguments?.getParcelable(USER_KEY)
          }

          userData?.let { user ->
               val unknownValue = getString(R.string.unknown_value)

               binding.title.text = "${user.firstName ?: unknownValue} details"

               val address = user.address
               val userInfo = buildString {
                    appendLine("ID: ${user.id ?: unknownValue}")
                    appendLine("Name: ${user.firstName ?: unknownValue} ${user.lastName ?: unknownValue}")
                    appendLine("Age: ${user.age ?: unknownValue}")
                    appendLine("Email: ${user.email ?: unknownValue}")
                    appendLine("Phone number: ${user.phone ?: unknownValue}")
                    appendLine(
                         "Address: " +
                                 "${address?.address ?: unknownValue}, " +
                                 "${address?.state ?: unknownValue}, " +
                                 "${address?.city ?: unknownValue}, " +
                                 "${address?.postalCode ?: unknownValue}"
                    )
               }
               binding.description.text = userInfo
          }
     }

     companion object {
          private const val USER_KEY = "user_key"
          fun newInstance(user: User): BottomSheetFragment = BottomSheetFragment().apply {
               arguments = bundleOf(USER_KEY to user)
          }
     }
}