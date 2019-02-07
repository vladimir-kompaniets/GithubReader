package com.vkompaniets.githubreader.di

import com.vkompaniets.githubreader.network.GithubService
import com.vkompaniets.githubreader.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun service(retrofit: Retrofit): GithubService = retrofit.create(GithubService::class.java)
}