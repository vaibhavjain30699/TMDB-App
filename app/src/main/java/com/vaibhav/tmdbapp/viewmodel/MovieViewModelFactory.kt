package com.vaibhav.tmdbapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vaibhav.tmdbapp.data.MovieRepository

class MoviesViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModelImpl(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}