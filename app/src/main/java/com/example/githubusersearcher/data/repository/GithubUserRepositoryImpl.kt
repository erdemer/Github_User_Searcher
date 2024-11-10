package com.example.githubusersearcher.data.repository

import android.util.Log
import com.example.githubusersearcher.data.model.detail.UserDetailResponse
import com.example.githubusersearcher.data.model.search.SearchResponse
import com.example.githubusersearcher.data.remote.NetworkServiceApi
import com.example.githubusersearcher.domain.repository.GithubUserRepository
import javax.inject.Inject

class GithubUserRepositoryImpl @Inject constructor(private val api: NetworkServiceApi) :
    GithubUserRepository {
    override suspend fun getUserBySearch(q: String): SearchResponse = api.getSearchUser(q)
    override suspend fun getUserDetail(userName: String): UserDetailResponse = api.getDetailUser(userName)
}