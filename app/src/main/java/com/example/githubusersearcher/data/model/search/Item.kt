package com.example.githubusersearcher.data.model.search

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("events_url")
    val eventsUrl: String? = null,
    @SerializedName("followers_url")
    val followersUrl: String? = null,
    @SerializedName("following_url")
    val followingUrl: String,
    @SerializedName("gists_url")
    val gistsUrl: String,
    @SerializedName("gravatar_id")
    val gravatarId: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val id: Int,
    val login: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("organizations_url")
    val organizationsUrl: String,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String,
    @SerializedName("repos_url")
    val reposUrl: String,
    val score: Double,
    @SerializedName("site_admin")
    val siteAdmin: Boolean,
    @SerializedName("starred_url")
    val starredUrl: String,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String,
    val type: String,
    val url: String,
    @SerializedName("user_view_type")
    val userViewType: String
)