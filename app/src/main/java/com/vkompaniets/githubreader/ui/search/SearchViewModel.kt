package com.vkompaniets.githubreader.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vkompaniets.githubreader.data.GithubDataSource
import com.vkompaniets.githubreader.data.GithubRepository
import com.vkompaniets.githubreader.model.User
import com.vkompaniets.githubreader.ui.UiResource
import javax.inject.Inject

class SearchViewModel @Inject constructor (private val githubRepository: GithubRepository): ViewModel(),
    GithubDataSource.GetUsersCallback {

    private val _searchResult = MutableLiveData<UiResource<List<User>>>()

    val searchResult: LiveData<UiResource<List<User>>>
        get() = _searchResult

    init {
        _searchResult.value = UiResource(emptyList(), false, null)
    }

    fun searchForUsers(query: String){
        if (query.isBlank())
            return

        _searchResult.value = UiResource(emptyList(), true, null)

        githubRepository.getUsers(query, this)
    }

    override fun onUsersLoaded(users: List<User>) {
        _searchResult.value = UiResource(users, false, null)
    }

    override fun onDataNotAvailable(message: String) {
        _searchResult.value = UiResource(emptyList(), false, message)
    }


}