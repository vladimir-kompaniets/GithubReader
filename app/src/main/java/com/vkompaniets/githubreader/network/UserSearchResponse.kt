package com.vkompaniets.githubreader.network

import com.google.gson.annotations.SerializedName
import com.vkompaniets.githubreader.model.User

data class UserSearchResponse (
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @SerializedName("items")
    val users: List<User>
)