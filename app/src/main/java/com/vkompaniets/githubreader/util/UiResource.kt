package com.vkompaniets.githubreader.util

data class UiResource<T> (val data: T, val loading: Boolean, val errorMessage: String?)
