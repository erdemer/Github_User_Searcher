package com.example.githubusersearcher.presentation.userList

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusersearcher.common.Constants
import com.example.githubusersearcher.common.Resource
import com.example.githubusersearcher.data.local.entity.UserEntity
import com.example.githubusersearcher.data.model.search.Item
import com.example.githubusersearcher.data.model.search.SearchResponse
import com.example.githubusersearcher.domain.useCase.AddUserToFavoritesUseCase
import com.example.githubusersearcher.domain.useCase.GetUserListUseCase
import com.example.githubusersearcher.presentation.userList.uiModel.UserUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAllUserUseCase: GetUserListUseCase,
    private val addFavoriteUseCase: AddUserToFavoritesUseCase
) : ViewModel() {
    val searchKeyword = savedStateHandle.get<String>(Constants.ARG_SEARCH_KEYWORD)

    private val _state = MutableStateFlow(UserListUIState())
    val state: StateFlow<UserListUIState> = _state.asStateFlow()

    init {
//        searchKeyword?.let {
//            getUsers(it)
//        }
    }

    fun getUsers(searchKeyword: String) {
        getAllUserUseCase(searchKeyword).onEach { result: Resource<List<UserUIModel>> ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UserListUIState(users = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = UserListUIState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = UserListUIState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addUserToFavorites(model: UserUIModel) {
        addFavoriteUseCase(
            UserEntity(
                userId = model.userId ?: 0L,
                name = model.name ?: "",
                isFavorite = model.isFavorite,
                avatarUrl = model.avatarUrl ?: ""
            )
        ).onEach { result ->
            if (result.data == true) {
                Log.d("UserListViewModel", "User added to favorites: ${model.name}")
            }
        }.launchIn(viewModelScope)
    }
}
