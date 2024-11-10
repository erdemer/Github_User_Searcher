package com.example.githubusersearcher.domain.useCase

import com.example.githubusersearcher.common.Resource
import com.example.githubusersearcher.data.local.entity.UserEntity
import com.example.githubusersearcher.data.model.search.SearchResponse
import com.example.githubusersearcher.domain.repository.GithubUserRepository
import com.example.githubusersearcher.presentation.userList.uiModel.UserUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(private val repository: GithubUserRepository) {
    operator fun invoke(searchKeyword: String): Flow<Resource<List<UserUIModel>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getUserBySearch(searchKeyword)
            val db = repository.getAllFavoriteUser()
            emit(Resource.Success(mapToUserUIModel(response, db)))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    private fun mapToUserUIModel(
        response: SearchResponse,
        db: List<UserEntity>
    ): List<UserUIModel> {
        return response.items?.mapIndexed { index, item ->
            UserUIModel(
                userId = item.id,
                name = item.login,
                isFavorite = db.any { it.userId == item.id },
                avatarUrl = item.avatarUrl,
                timeStamp = db.find { it.userId == item.id }?.timeStamps
            )
        }.orEmpty()
    }
}