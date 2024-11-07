package com.example.githubusersearcher.data.model.search

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean = false,
    val items: List<Item>? = null,
    @SerializedName("total_count")
    val totalCount: Int? = null
)