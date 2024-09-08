package com.vaibhav.tmdbapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.vaibhav.tmdbapp.data.MovieRepository

class MoviesViewModelFactory(
    private val repository: MovieRepository,
    private val navController: NavController,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModelImpl(repository, navController) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}