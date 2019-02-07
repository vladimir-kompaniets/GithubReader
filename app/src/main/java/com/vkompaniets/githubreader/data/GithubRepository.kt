package com.vkompaniets.githubreader.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.vkompaniets.githubreader.model.User
import com.vkompaniets.githubreader.network.GithubService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(private val githubService: GithubService) {

    fun getUsers(login: String): LiveData<List<User>> = Transformations.map(githubService.searchUsers(login)) { it.users }
}