package com.vkompaniets.githubreader.network

import android.arch.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface GithubService {

    @Headers("Accept: application/vnd.github.v3.full+json")
    @GET("search/users")
    fun searchUsers(@Query("q") login: String): LiveData<UserSearchResponse>
}