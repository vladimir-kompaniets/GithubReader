package com.vkompaniets.githubreader.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(private val githubRemoteDataSource: GithubRemoteDataSource) :
    GithubDataSource by githubRemoteDataSource