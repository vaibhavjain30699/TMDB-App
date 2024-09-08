package com.vaibhav.tmdbapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaibhav.tmdbapp.data.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface MovieViewModel {
    val state: MutableStateFlow<MoviesState>
    fun fetchTrendingMoviesData()
    fun onRetry()
}

class MovieViewModelImpl(
    private val repository: MovieRepository,
) : MovieViewModel, ViewModel() {

    override val state: MutableStateFlow<MoviesState> = MutableStateFlow(MoviesState())

    init {
        fetchTrendingMoviesData()
    }

    override fun fetchTrendingMoviesData() {
        viewModelScope.launch {
            repository.fetchTrendingMovies().collect { data ->
                state.update { it.copy(movies = data) }
            }
        }
    }

    override fun onRetry() {
        fetchTrendingMoviesData()
    }
}