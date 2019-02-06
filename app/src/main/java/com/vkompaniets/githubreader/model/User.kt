package com.vkompaniets.githubreader.model

data class User(
    val id: Long,
    val login: String,
    val avatar: String,
    val company: String?,
    val followers: Int = 0,
    val following: Int = 0
)