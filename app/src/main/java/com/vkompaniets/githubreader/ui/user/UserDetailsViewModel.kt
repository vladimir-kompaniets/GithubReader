package com.vkompaniets.githubreader.ui.user

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vkompaniets.githubreader.data.GithubDataSource
import com.vkompaniets.githubreader.data.GithubRepository
import com.vkompaniets.githubreader.model.Repo
import com.vkompaniets.githubreader.model.User
import com.vkompaniets.githubreader.ui.UiResource
import javax.inject.Inject


class UserDetailsViewModel @Inject constructor(private val githubRepository: GithubRepository):

    ViewModel(), GithubDataSource.GetUserCallback, GithubDataSource.GetReposCallback{

    private val _userResult = MutableLiveData<UiResource<User?>>()
    private val _reposResult = MutableLiveData<UiResource<List<Repo>>>()
    private var login: String? = null

    val userResult: LiveData<UiResource<User?>>
        get() = _userResult

    val reposResult: LiveData<UiResource<List<Repo>>>
        get() = _reposResult

    fun loadData(login: String){
        if (login.isBlank())
            return

        this.login = login
        _userResult.value = UiResource(null, true, null)
        _reposResult.value = UiResource(emptyList(), true, null)

        githubRepository.getUser(login, this)
        githubRepository.getRepos(login, this)

    }

    override fun onUserLoaded(user: User) {
        _userResult.value = UiResource(user, false, null)
    }

    override fun onUserNotAvailable(message: String) {
        _userResult.value = UiResource(null, false, "can not load user $login")
    }

    override fun onReposLoaded(users: List<Repo>) {
        _reposResult.value = UiResource(users, false, null)
    }

    override fun onRepoNotAvailable(message: String) {
        _reposResult.value = UiResource(emptyList(), false, "can not load repos for user $login")
    }

}