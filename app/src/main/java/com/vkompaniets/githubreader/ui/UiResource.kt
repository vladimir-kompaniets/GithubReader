package com.vkompaniets.githubreader.ui

data class UiResource<T> (val data: T, val loading: Boolean, val errorMessage: String?)
