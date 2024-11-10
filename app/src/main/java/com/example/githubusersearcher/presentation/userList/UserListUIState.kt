package com.example.githubusersearcher.presentation.userList

import com.example.githubusersearcher.presentation.userList.uiModel.UserUIModel

data class UserListUIState(
    val isLoading: Boolean = false,
    val users: List<UserUIModel> = emptyList(),
    val error: String = "",
    val recyclerViewItemPosition: Int = 0
)
