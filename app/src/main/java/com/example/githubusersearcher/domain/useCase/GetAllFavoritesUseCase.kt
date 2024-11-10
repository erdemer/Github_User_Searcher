package com.example.githubusersearcher.domain.useCase

import com.example.githubusersearcher.common.Resource
import com.example.githubusersearcher.data.local.entity.UserEntity
import com.example.githubusersearcher.domain.repository.GithubUserRepository
import com.example.githubusersearcher.presentation.favorites.FavoritesUIState
import com.example.githubusersearcher.presentation.userList.uiModel.UserUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllFavoritesUseCase @Inject constructor(private val userRepository: GithubUserRepository) {
    operator fun invoke(): Flow<Resource<FavoritesUIState>> = flow {
        try {
            emit(Resource.Loading())
            val users = userRepository.getAllFavoriteUser()
            emit(Resource.Success(mapToUserUIModel(users)))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }

    private fun mapToUserUIModel(users: List<UserEntity>): FavoritesUIState {
        return FavoritesUIState(users = users.map {
            UserUIModel(
                userId = it.userId,
                name = it.name,
                isFavorite = it.isFavorite,
                avatarUrl = it.avatarUrl
            )
        })
    }
}