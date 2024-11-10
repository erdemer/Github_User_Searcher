package com.example.githubusersearcher.presentation.userList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.githubusersearcher.databinding.ItemUserListBinding
import com.example.githubusersearcher.presentation.userList.UserListAdapter.ItemViewHolder
import com.example.githubusersearcher.presentation.userList.uiModel.UserUIModel
import com.example.githubusersearcher.R


class UserListAdapter(
    private val onClick: (UserUIModel) -> Unit,
    private val onFavoriteClick: (UserUIModel) -> Unit
) : ListAdapter<UserUIModel, ItemViewHolder>(ItemDiffCallback()) {
    inner class ItemViewHolder(private val binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserUIModel, position: Int) {
            binding.tvProfileName.text = item.name
            binding.ivProfilePic.load(item.avatarUrl)
            if (!item.isFavorite) {
                binding.ivFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
            } else {
                binding.ivFavorite.setImageResource(R.drawable.baseline_favorite_24)
            }

            binding.root.setOnClickListener {
                onClick(item)
            }

            if (position != itemCount - 1) {
                binding.divider.visibility = View.VISIBLE
            } else {
                binding.divider.visibility = View.GONE
            }
            binding.ivFavorite.setOnClickListener {
                item.isFavorite = !item.isFavorite
                onFavoriteClick(item)
                notifyItemChanged(position)
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding =
            ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }
}

class ItemDiffCallback : DiffUtil.ItemCallback<UserUIModel>() {
    override fun areItemsTheSame(oldItem: UserUIModel, newItem: UserUIModel): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: UserUIModel, newItem: UserUIModel): Boolean {
        return oldItem == newItem
    }
}