package com.vkompaniets.githubreader.data

import com.vkompaniets.githubreader.network.GithubService
import com.vkompaniets.githubreader.network.UserSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRemoteDataSource @Inject constructor(private val service: GithubService) : GithubDataSource {

    override fun getUsers(query: String, getUsersCallback: GithubDataSource.GetUsersCallback) {
        service.searchUsers(query).enqueue(object: Callback<UserSearchResponse>{

            override fun onFailure(call: Call<UserSearchResponse>, t: Throwable) {
                getUsersCallback.onDataNotAvailable(t.message ?: "unknown error!!!")
            }

            override fun onResponse(call: Call<UserSearchResponse>, response: Response<UserSearchResponse>) {
                val users = response.body()?.users
                if (users.isNullOrEmpty()){
                    getUsersCallback.onDataNotAvailable("user not found")
                }else{
                    getUsersCallback.onUsersLoaded(users)
                }
            }

        })
    }
}