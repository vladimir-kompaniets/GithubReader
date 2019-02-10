package com.vkompaniets.githubreader.data

import com.vkompaniets.githubreader.model.Repo
import com.vkompaniets.githubreader.model.User

interface GithubDataSource {

    interface GetUsersCallback {
        fun onUsersLoaded(users: List<User>)

        fun onDataNotAvailable(message: String)
    }

    interface GetUserCallback {
        fun onUserLoaded(user: User)

        fun onUserNotAvailable(message: String)
    }

    interface GetReposCallback {
        fun onReposLoaded(users: List<Repo>)

        fun onRepoNotAvailable(message: String)
    }

    fun getUsers(query: String, getUsersCallback: GetUsersCallback)

    fun getUser(login: String, getUserCallback: GetUserCallback)

    fun getRepos(login: String, getReposCallback: GetReposCallback)
}