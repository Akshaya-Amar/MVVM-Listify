package com.example.mvvmretrofitkotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        arguments?.let {
            val user = it.getParcelable<User>(USER_KEY)
            user?.let {
                binding.infoTextView.text = user.firstName
            }
        }
    }

    companion object {
        private const val USER_KEY = "user_key"
        fun newInstance(user: User): BottomSheetFragment {
            val fragment = BottomSheetFragment()
            val bundle = Bundle().apply {
                putParcelable(USER_KEY, user)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}