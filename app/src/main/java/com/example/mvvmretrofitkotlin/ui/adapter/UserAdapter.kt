package com.example.mvvmretrofitkotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmretrofitkotlin.data.model.User
import com.example.mvvmretrofitkotlin.databinding.UserItemBinding

class UserAdapter(
     private val onItemClick: (User) -> Unit
) : ListAdapter<User, UserAdapter.UserViewHolder>(DiffCallBack()) {

     class DiffCallBack : DiffUtil.ItemCallback<User>() {
          override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
               return oldItem.id == newItem.id
          }

          override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
               return oldItem == newItem
          }
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
          val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
          return UserViewHolder(binding)
     }

     override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
          val user = getItem(position)
          holder.bind(user, onItemClick)
     }

     class UserViewHolder(
          private val binding: UserItemBinding
     ) : RecyclerView.ViewHolder(binding.root) {
          fun bind(user: User, onItemClick: (User) -> Unit) {
               with(binding) {
                    val fullName = "${user.firstName} ${user.lastName}"
                    name.text = fullName
                    email.text = user.email
                    phoneNumber.text = user.phone
                    root.setOnClickListener {
                         onItemClick(user)
                    }
               }
          }
     }
}