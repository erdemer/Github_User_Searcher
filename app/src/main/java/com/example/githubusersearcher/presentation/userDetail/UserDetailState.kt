package com.example.githubusersearcher.presentation.userDetail

import com.example.githubusersearcher.presentation.userDetail.uiModel.UserDetailUIModel

data class UserDetailState(
    val isLoading: Boolean = false,
    val user: UserDetailUIModel? = null,
    val error: String = ""
)
