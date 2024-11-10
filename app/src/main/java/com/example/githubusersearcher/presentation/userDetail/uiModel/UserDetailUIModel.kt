package com.example.githubusersearcher.presentation.userDetail.uiModel

data class UserDetailUIModel(
    val userId: Long? = null,
    val name: String? = null,
    val bio: String? = null,
    val location: String? = null,
    val followers: Int? = null,
    val following: Int? = null,
    val publicRepos: Int? = null,
    val avatarUrl: String? = null,
    val favoriteAdditionDate: String? = null,
    val isFavorite: Boolean = false,
    val login: String? = null
)
