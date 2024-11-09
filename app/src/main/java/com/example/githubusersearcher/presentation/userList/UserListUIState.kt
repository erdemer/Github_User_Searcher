package com.example.githubusersearcher.presentation.userList

import com.example.githubusersearcher.data.model.search.Item

data class UserListUIState(
    val isLoading: Boolean = false,
    val users: List<Item> = emptyList(),
    val error: String = ""
)
