package com.example.githubusersearcher.presentation.userDetail

import com.example.githubusersearcher.data.model.detail.UserDetailResponse

data class UserDetailState(
    val isLoading: Boolean = false,
    val user: UserDetailResponse? = null,
    val error: String = ""
)
