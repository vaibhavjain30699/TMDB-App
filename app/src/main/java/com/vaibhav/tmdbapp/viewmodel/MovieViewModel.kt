package com.vaibhav.tmdbapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.vaibhav.tmdbapp.data.Movie
import com.vaibhav.tmdbapp.data.MovieRepository
import com.vaibhav.tmdbapp.navigation.MovieDetailScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface MovieViewModel {
    val state: MutableStateFlow<MoviesState>
    fun fetchTrendingMoviesData()
    fun onMovieItemThumbnailClick(movie: Movie)
    fun onRetry()
}

class MovieViewModelImpl(
    private val repository: MovieRepository,
    private val navController: NavController,
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

    override fun onMovieItemThumbnailClick(movie: Movie) {
        navController.navigate(MovieDetailScreen(movie.posterPath, movie.title, movie.overview))
    }

    override fun onRetry() {
        fetchTrendingMoviesData()
    }
}