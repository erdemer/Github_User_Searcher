package com.example.githubusersearcher.presentation.userDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusersearcher.common.Constants
import com.example.githubusersearcher.common.Resource
import com.example.githubusersearcher.data.model.detail.UserDetailResponse
import com.example.githubusersearcher.domain.useCase.GetUserDetailUseCase
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
    private val userDetailUseCase: GetUserDetailUseCase
) : ViewModel() {
    private val userName = savedStateHandle.get<String>(Constants.ARG_USER_NAME)
    val isFavorite = savedStateHandle.get<Boolean>(Constants.ARG_IS_FAVORITE)

    private val _state = MutableStateFlow(UserDetailState())
    val state: StateFlow<UserDetailState> = _state.asStateFlow()

    init {
        userName?.let {
            getUserDetail(it)
        }
    }

    private fun getUserDetail(userName: String) {
        userDetailUseCase(userName).onEach { result: Resource<UserDetailResponse> ->
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
}
