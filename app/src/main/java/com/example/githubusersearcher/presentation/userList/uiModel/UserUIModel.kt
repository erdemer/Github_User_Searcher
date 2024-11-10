package com.example.githubusersearcher.presentation.userList.uiModel

data class UserUIModel(
    val userId: Long? = null,
    val name: String? = null,
    var isFavorite: Boolean = false,
    val avatarUrl: String? = null,
    val timeStamp: Long? = null,
)
