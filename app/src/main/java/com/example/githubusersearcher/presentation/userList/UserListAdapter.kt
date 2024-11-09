package com.example.githubusersearcher.presentation.userList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.githubusersearcher.data.model.search.Item
import com.example.githubusersearcher.databinding.ItemUserListBinding
import com.example.githubusersearcher.presentation.userList.UserListAdapter.ItemViewHolder


class UserListAdapter: ListAdapter<Item, ItemViewHolder>(ItemDiffCallback()) {
    inner class ItemViewHolder(private val binding: ItemUserListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item, position: Int) {
            binding.tvProfileName.text = item.login
            binding.ivProfilePic.load(item.avatarUrl)

            if (position != itemCount - 1) {
                binding.divider.visibility = View.VISIBLE
            } else {
                binding.divider.visibility = View.GONE
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }
}

    class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}