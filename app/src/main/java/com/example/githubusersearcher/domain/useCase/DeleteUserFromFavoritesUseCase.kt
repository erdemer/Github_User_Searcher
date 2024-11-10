package com.example.githubusersearcher.domain.useCase

import com.example.githubusersearcher.common.Resource
import com.example.githubusersearcher.domain.repository.GithubUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteUserFromFavoritesUseCase @Inject constructor(private val repository: GithubUserRepository) {
    operator fun invoke(userId: Long): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            repository.deleteUserFromFavorites(userId)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            emit(Resource.Success(false))
        }
    }
}