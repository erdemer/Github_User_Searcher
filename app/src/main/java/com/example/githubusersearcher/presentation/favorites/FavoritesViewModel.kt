package com.example.githubusersearcher.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusersearcher.common.Resource
import com.example.githubusersearcher.domain.useCase.GetAllFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val allFavoriteUseCase: GetAllFavoritesUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow(FavoritesUIState())
    val state: StateFlow<FavoritesUIState> = _state.asStateFlow()

    init {
        getAllFavorites()
    }

    private fun getAllFavorites() {
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
}
