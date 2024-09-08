package com.vaibhav.tmdbapp.data

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val exception: Throwable? = null) : Result<T>()
    data object Loading : Result<Nothing>()
}