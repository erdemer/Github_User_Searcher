package com.example.githubusersearcher.data.model.detail

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    val bio: String? = null,
    val blog: String? = null,
    val company: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    val email: String? = null,
    @SerializedName("events_url")
    val eventsUrl: String? = null,
    val followers: Int? = null,
    @SerializedName("followers_url")
    val followersUrl: String? = null,
    val following: Int? = null,
    @SerializedName("following_url")
    val followingUrl: String? = null,
    @SerializedName("gists_url")
    val gistsUrl: String? = null,
    @SerializedName("gravatar_id")
    val gravatarId: String? = null,
    val hireable: Any,
    @SerializedName("html_url")
    val htmlUrl: String? = null,
    val id: Long? = null,
    val location: String? = null,
    val login: String? = null,
    val name: String? = null,
    @SerializedName("node_id")
    val nodeId: String? = null,
    @SerializedName("organizations_url")
    val organizationsUrl: String? = null,
    @SerializedName("public_gists")
    val publicGists: Int? = null,
    @SerializedName("public_repos")
    val publicRepos: Int? = null,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String? = null,
    @SerializedName("repos_url")
    val reposUrl: String? = null,
    @SerializedName("site_admin")
    val siteAdmin: Boolean = false,
    @SerializedName("starred_url")
    val starredUrl: String? = null,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String? = null,
    @SerializedName("twitter_username")
    val twitterUsername: String? = null,
    val type: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
    val url: String? = null,
    @SerializedName("user_view_type")
    val userViewType: String? = null
)