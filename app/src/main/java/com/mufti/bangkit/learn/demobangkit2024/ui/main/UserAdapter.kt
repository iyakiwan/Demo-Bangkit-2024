package com.mufti.bangkit.learn.demobangkit2024.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.mufti.bangkit.learn.demobangkit2024.R
import com.mufti.bangkit.learn.demobangkit2024.databinding.ItemListUserBinding
import com.mufti.bangkit.learn.demobangkit2024.model.User

class UserAdapter : PagingDataAdapter<User, UserAdapter.ListViewHolder>(diffCallback) {

    private var onUserSelected: (User) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user, onUserSelected)
        }
    }

    class ListViewHolder(private var binding: ItemListUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, onUserSelected: (User) -> Unit) {
            Glide.with(binding.root)
                .load(user.avatar)
                .apply(RequestOptions().override(80, 80).placeholder(R.drawable.ic_avatar))
                .transform(CircleCrop())
                .into(binding.itemAvatar)

            binding.itemName.text = binding.root.context.getString(
                R.string.format_full_name,
                user.firstName,
                user.lastName
            )
            binding.itemEmail.text = user.email

            binding.root.setOnClickListener {
                onUserSelected(user)
            }
        }
    }

    fun setOnUserSelected(onUserSelected: (User) -> Unit) {
        this.onUserSelected = onUserSelected
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}
