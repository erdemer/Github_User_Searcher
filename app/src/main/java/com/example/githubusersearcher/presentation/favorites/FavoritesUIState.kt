package com.example.githubusersearcher.presentation.favorites

import com.example.githubusersearcher.presentation.userList.uiModel.UserUIModel

data class FavoritesUIState(
    val isLoading: Boolean = false,
    val users: List<UserUIModel> = emptyList(),
    val error: String = ""
)
