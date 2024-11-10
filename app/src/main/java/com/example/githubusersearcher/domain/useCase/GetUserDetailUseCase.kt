package com.example.githubusersearcher.domain.useCase

import com.example.githubusersearcher.common.Resource
import com.example.githubusersearcher.data.model.detail.UserDetailResponse
import com.example.githubusersearcher.domain.repository.GithubUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(private val repository: GithubUserRepository) {
    operator fun invoke(userName: String): Flow<Resource<UserDetailResponse>> = flow {
        try {
            emit(Resource.Loading<UserDetailResponse>())
            val response = repository.getUserDetail(userName)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}
