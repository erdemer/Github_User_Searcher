package com.example.githubusersearcher.domain.useCase

import com.example.githubusersearcher.common.Resource
import com.example.githubusersearcher.data.local.entity.UserEntity
import com.example.githubusersearcher.domain.repository.GithubUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddUserToFavoritesUseCase @Inject constructor(private val userRepository: GithubUserRepository) {
    operator fun invoke(user: UserEntity ): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            userRepository.addUserToFavorite(user)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            emit(Resource.Success(false))
        }
    }
}