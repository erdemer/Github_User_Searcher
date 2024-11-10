package com.example.githubusersearcher.presentation.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusersearcher.common.Resource
import com.example.githubusersearcher.domain.useCase.DeleteUserFromFavoritesUseCase
import com.example.githubusersearcher.domain.useCase.GetAllFavoritesUseCase
import com.example.githubusersearcher.presentation.userList.uiModel.UserUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val allFavoriteUseCase: GetAllFavoritesUseCase,
    private val deleteUserFromFavoritesUseCase: DeleteUserFromFavoritesUseCase
) :
    ViewModel() {
    private val _state = MutableStateFlow(FavoritesUIState())
    val state: StateFlow<FavoritesUIState> = _state.asStateFlow()

    fun getAllFavorites() {
        allFavoriteUseCase().onEach { result: Resource<FavoritesUIState> ->
            when (result) {
                is Resource.Success -> {
                    _state.value = FavoritesUIState(users = result.data?.users ?: emptyList())
                }

                is Resource.Loading -> {
                    _state.value = FavoritesUIState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = FavoritesUIState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun removeFavorite(response: UserUIModel?) {
        response?.userId?.let { userId ->
            deleteUserFromFavoritesUseCase(userId).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data == true) {
                            Log.d(
                                "UserDetailViewModel",
                                "User removed from favorites: ${response.name}"
                            )
                            getAllFavorites()
                        }
                    }

                    is Resource.Error -> {
                        _state.value = FavoritesUIState(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = FavoritesUIState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}
