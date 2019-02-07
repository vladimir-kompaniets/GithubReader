package com.vkompaniets.githubreader.di

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface GithubService {

    @Headers("Accept: application/vnd.github.v3.full+json")
    @GET("search/users?q=in:login+{login}")
    fun getUsers(@Path("login") login: String): Call<Any>
}