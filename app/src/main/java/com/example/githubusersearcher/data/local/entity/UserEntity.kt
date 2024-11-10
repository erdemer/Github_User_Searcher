package com.example.githubusersearcher.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = UUID.randomUUID().mostSignificantBits,
    val userId: Long,
    val name: String,
    val avatarUrl: String,
    val isFavorite: Boolean,
    val timeStamps: Long = System.currentTimeMillis()
)
