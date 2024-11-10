package com.example.githubusersearcher.domain.useCase

import com.example.githubusersearcher.common.Resource
import com.example.githubusersearcher.data.local.entity.UserEntity
import com.example.githubusersearcher.data.model.detail.UserDetailResponse
import com.example.githubusersearcher.domain.repository.GithubUserRepository
import com.example.githubusersearcher.presentation.userDetail.uiModel.UserDetailUIModel
import com.example.githubusersearcher.util.DateUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(private val repository: GithubUserRepository) {
    operator fun invoke(userName: String): Flow<Resource<UserDetailUIModel>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getUserDetail(userName)
            val db = repository.getAllFavoriteUser()
            emit(Resource.Success(mapToUiModel(response, db)))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    private fun mapToUiModel(
        response: UserDetailResponse,
        db: List<UserEntity>
    ): UserDetailUIModel {
        val matchingUser = db.find { it.userId == response.id }
        return UserDetailUIModel(
            userId = response.id,
            name = response.login,
            bio = response.bio,
            location = response.location,
            followers = response.followers,
            following = response.following,
            publicRepos = response.publicRepos,
            avatarUrl = response.avatarUrl,
            favoriteAdditionDate = matchingUser?.timeStamps?.let {
                DateUtil.convertMillisToTurkishDate(
                    it
                )
            },
            isFavorite = matchingUser?.isFavorite == true,
        )
    }
}