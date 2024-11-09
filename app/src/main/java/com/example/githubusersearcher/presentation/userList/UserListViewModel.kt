package com.example.githubusersearcher.presentation.userList

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusersearcher.common.Constants
import com.example.githubusersearcher.common.Resource
import com.example.githubusersearcher.data.model.search.SearchResponse
import com.example.githubusersearcher.domain.useCase.GetUserListUseCase
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
    private val useCase: GetUserListUseCase
) : ViewModel() {
    val searchKeyword = savedStateHandle.get<String>(Constants.ARG_SEARCH_KEYWORD)

    private val _state = MutableStateFlow(UserListUIState())
    val state: StateFlow<UserListUIState> = _state.asStateFlow()

    init {
        searchKeyword?.let {
            getUsers(it)
        }
    }

    private fun getUsers(searchKeyword: String) {
        useCase(searchKeyword).onEach { result: Resource<SearchResponse> ->
            when(result) {
                is Resource.Success -> {
                    _state.value = UserListUIState(users = result.data?.items ?: emptyList())
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

}