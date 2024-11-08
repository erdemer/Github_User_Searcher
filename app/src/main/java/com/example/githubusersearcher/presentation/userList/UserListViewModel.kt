package com.example.githubusersearcher.presentation.userList

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.githubusersearcher.common.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    val searchKeyword = savedStateHandle.get<String>(Constants.ARG_SEARCH_KEYWORD)


}