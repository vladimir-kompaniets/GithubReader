package com.vkompaniets.githubreader.network

import com.vkompaniets.githubreader.model.Repo
import com.vkompaniets.githubreader.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GithubService {

    @GET("search/users")
    fun searchUsers(@Query("q") login: String): Call<UserSearchResponse>

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Call<User>

    @GET("users/{login}/repos")
    fun getRepos(@Path("login") login: String): Call<List<Repo>>

}