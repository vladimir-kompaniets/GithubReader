package com.vkompaniets.githubreader.data

import com.vkompaniets.githubreader.model.User

interface GithubDataSource {

    interface GetUsersCallback {
        fun onUsersLoaded(users: List<User>)

        fun onDataNotAvailable(message: String)
    }

    fun getUsers(query: String, getUsersCallback: GetUsersCallback)
}