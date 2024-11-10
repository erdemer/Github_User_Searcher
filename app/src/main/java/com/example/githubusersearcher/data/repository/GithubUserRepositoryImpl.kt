package com.example.githubusersearcher.data.repository

import com.example.githubusersearcher.data.local.dao.UserDao
import com.example.githubusersearcher.data.local.entity.UserEntity
import com.example.githubusersearcher.data.model.detail.UserDetailResponse
import com.example.githubusersearcher.data.model.search.SearchResponse
import com.example.githubusersearcher.data.remote.NetworkServiceApi
import com.example.githubusersearcher.domain.repository.GithubUserRepository
import javax.inject.Inject

class GithubUserRepositoryImpl @Inject constructor(private val api: NetworkServiceApi, private val userDao: UserDao) :
    GithubUserRepository {
    override suspend fun getUserBySearch(q: String): SearchResponse = api.getSearchUser(q)
    override suspend fun getUserDetail(userName: String): UserDetailResponse = api.getDetailUser(userName)
    override suspend fun addUserToFavorite(userEntity: UserEntity) = userDao.insertUser(userEntity)
    override suspend fun getAllFavoriteUser(): List<UserEntity> = userDao.getAllUsers()
}