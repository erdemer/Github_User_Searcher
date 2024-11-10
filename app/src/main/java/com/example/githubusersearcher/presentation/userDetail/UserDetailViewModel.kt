package com.example.githubusersearcher.presentation.userDetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusersearcher.common.Constants
import com.example.githubusersearcher.common.Resource
import com.example.githubusersearcher.data.local.entity.UserEntity
import com.example.githubusersearcher.domain.useCase.AddUserToFavoritesUseCase
import com.example.githubusersearcher.domain.useCase.DeleteUserFromFavoritesUseCase
import com.example.githubusersearcher.domain.useCase.GetUserDetailUseCase
import com.example.githubusersearcher.presentation.userDetail.uiModel.UserDetailUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userDetailUseCase: GetUserDetailUseCase,
    private val addUserToFavoritesUseCase: AddUserToFavoritesUseCase,
    private val deleteUserFromFavoritesUseCase: DeleteUserFromFavoritesUseCase
) : ViewModel() {
    private val userName = savedStateHandle.get<String>(Constants.ARG_USER_NAME)

    private val _state = MutableStateFlow(UserDetailState())
    val state: StateFlow<UserDetailState> = _state.asStateFlow()

    init {
        userName?.let {
            getUserDetail(it)
        }
    }

    private fun getUserDetail(userName: String) {
        userDetailUseCase(userName).onEach { result: Resource<UserDetailUIModel> ->
            when(result) {
                is Resource.Success -> {
                    _state.value = UserDetailState(user = result.data)
                }
                is Resource.Error -> {
                    _state.value = UserDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = UserDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun manageFavorite(response: UserDetailUIModel?) {
        if (response?.isFavorite == false) {
            addFavorite(response)
        } else {
            removeFavorite(response)
        }
    }

    private fun removeFavorite(response: UserDetailUIModel?) {
        response?.userId?.let { userId ->
            deleteUserFromFavoritesUseCase(userId).onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        if (result.data == true) {
                            Log.d("UserDetailViewModel", "User removed from favorites: ${response.name}")
                            userName?.let { getUserDetail(it) }
                        }
                    }
                    is Resource.Error -> {
                        _state.value = UserDetailState(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = UserDetailState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun addFavorite(response: UserDetailUIModel?) {
        addUserToFavoritesUseCase(
            UserEntity(
                userId = response?.userId ?: 0L,
                name = response?.name ?: "",
                isFavorite = true,
                avatarUrl = response?.avatarUrl ?: ""
            )
        ).onEach { result ->
            if (result.data == true) {
                Log.d("UserDetailViewModel", "User added to favorites: ${response?.name}")
                userName?.let { getUserDetail(it) }
            }
        }.launchIn(viewModelScope)
    }
}
