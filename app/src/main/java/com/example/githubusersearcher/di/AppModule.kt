package com.example.githubusersearcher.di

import com.example.githubusersearcher.data.remote.NetworkServiceApi
import com.example.githubusersearcher.data.remote.RetrofitClient
import com.example.githubusersearcher.data.repository.GithubUserRepositoryImpl
import com.example.githubusersearcher.domain.repository.GithubUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofitClient(): NetworkServiceApi {
        return RetrofitClient.retrofitClient().create(NetworkServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGithubUserRepository(networkServiceApi: NetworkServiceApi): GithubUserRepository {
        return GithubUserRepositoryImpl(networkServiceApi)
    }

}