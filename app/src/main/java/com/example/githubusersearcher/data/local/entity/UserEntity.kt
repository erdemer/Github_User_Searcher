package com.example.githubusersearcher.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: Int,
    val name: String,
    val bio: String,
    val location: String,
    val avatarUrl: String,
    val followers: Int,
    val following: Int,
    val publicRepos: Int
)
