package com.example.githubusersearcher.di

import android.content.Context
import androidx.room.Room
import com.example.githubusersearcher.data.local.UserDatabase
import com.example.githubusersearcher.data.local.dao.UserDao
import com.example.githubusersearcher.data.remote.NetworkServiceApi
import com.example.githubusersearcher.data.remote.RetrofitClient
import com.example.githubusersearcher.data.repository.GithubUserRepositoryImpl
import com.example.githubusersearcher.domain.repository.GithubUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return UserDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase) = userDatabase.userDao()

    @Provides
    @Singleton
    fun provideRetrofitClient(): NetworkServiceApi {
        return RetrofitClient.retrofitClient().create(NetworkServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGithubUserRepository(
        networkServiceApi: NetworkServiceApi,
        userDao: UserDao
    ): GithubUserRepository {
        return GithubUserRepositoryImpl(networkServiceApi, userDao)
    }


}