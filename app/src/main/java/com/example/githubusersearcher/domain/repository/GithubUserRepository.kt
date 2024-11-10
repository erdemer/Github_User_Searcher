package com.example.githubusersearcher.domain.repository

import com.example.githubusersearcher.data.local.entity.UserEntity
import com.example.githubusersearcher.data.model.detail.UserDetailResponse
import com.example.githubusersearcher.data.model.search.SearchResponse

interface GithubUserRepository {
    suspend fun getUserBySearch(q: String): SearchResponse
    suspend fun getUserDetail(userName: String): UserDetailResponse
    suspend fun addUserToFavorite(userEntity: UserEntity)
    suspend fun getAllFavoriteUser(): List<UserEntity>
    suspend fun deleteUserFromFavorites(userId: Long)
}