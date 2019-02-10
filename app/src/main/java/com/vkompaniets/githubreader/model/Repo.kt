package com.vkompaniets.githubreader.model

import com.google.gson.annotations.SerializedName

data class Repo (
    val name: String,
    val language: String?,
    @SerializedName("stargazers_count")
    val stars: Int,
    val forks: Int
)