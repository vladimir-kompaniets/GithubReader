package com.vkompaniets.githubreader.data

import com.vkompaniets.githubreader.model.Repo
import com.vkompaniets.githubreader.model.User
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
                getUsersCallback.onDataNotAvailable(t.message ?: "unknown error")
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

    override fun getUser(login: String, getUserCallback: GithubDataSource.GetUserCallback) {
        service.getUser(login).enqueue(object: Callback<User>{

            override fun onFailure(call: Call<User>, t: Throwable) {
                getUserCallback.onUserNotAvailable(t.message ?: "unknown error")
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                if (user == null){
                    getUserCallback.onUserNotAvailable("user not found")
                }else{
                    getUserCallback.onUserLoaded(user)
                }
            }

        })
    }

    override fun getRepos(login: String, getReposCallback: GithubDataSource.GetReposCallback) {
        service.getRepos(login).enqueue(object: Callback<List<Repo>>{
            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                getReposCallback.onRepoNotAvailable(t.message ?: "unknown error")
            }

            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                val repos = response.body()
                if (repos == null){
                    getReposCallback.onRepoNotAvailable("repository not found")
                }else{
                    getReposCallback.onReposLoaded(repos)
                }
            }

        })
    }
}