package com.vkompaniets.githubreader.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Long,
    val login: String,
    @SerializedName("avatar_url")
    val avatar: String,
    val company: String?,
    val followers: Int = 0,
    val following: Int = 0
)