package com.vkompaniets.githubreader.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.vkompaniets.githubreader.data.GithubRepository
import com.vkompaniets.githubreader.model.User
import javax.inject.Inject

class SearchViewModel @Inject constructor (private val githubRepository: GithubRepository): ViewModel() {

    private val searchQuery = MutableLiveData<String>()

    val searchResult: LiveData<List<User>> = Transformations
        .switchMap(searchQuery){
            githubRepository.getUsers(it)
        }

    fun searchUser (login: String){
        if (login.isBlank() || login == searchQuery.value)
            return

        searchQuery.value = login

    }
}