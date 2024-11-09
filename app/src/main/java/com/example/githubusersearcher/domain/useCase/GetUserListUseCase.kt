package com.example.githubusersearcher.domain.useCase

import com.example.githubusersearcher.common.Resource
import com.example.githubusersearcher.data.model.search.SearchResponse
import com.example.githubusersearcher.domain.repository.GithubUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(private val repository: GithubUserRepository) {
    operator fun invoke(searchKeyword: String): Flow<Resource<SearchResponse>> = flow {
        try {
            emit(Resource.Loading<SearchResponse>())
            val response = repository.getUserBySearch(searchKeyword)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}