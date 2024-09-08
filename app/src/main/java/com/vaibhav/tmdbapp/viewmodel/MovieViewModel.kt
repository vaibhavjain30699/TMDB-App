package com.vaibhav.tmdbapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.vaibhav.tmdbapp.data.Movie
import com.vaibhav.tmdbapp.data.MovieRepository
import com.vaibhav.tmdbapp.data.Result
import com.vaibhav.tmdbapp.navigation.MovieDetailScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface MovieViewModel {
    val state: MutableStateFlow<MoviesState>
    fun fetchTrendingMoviesData()
    fun onMovieItemThumbnailClick(movie: Movie)
    fun onSearchQueryUpdate(value: String)
    fun onRetry()
}

class MovieViewModelImpl(
    private val repository: MovieRepository,
    private val navController: NavController,
) : MovieViewModel, ViewModel() {

    override val state: MutableStateFlow<MoviesState> = MutableStateFlow(MoviesState())

    private val moviesList: MutableList<Movie> = mutableListOf()

    init {
        fetchTrendingMoviesData()
    }

    override fun fetchTrendingMoviesData() {
        viewModelScope.launch {
            repository.fetchTrendingMovies().collect { data ->
                state.update { it.copy(movies = data) }
                if (data is Result.Success) {
                    moviesList.addAll(data.data.results)
                }
            }
        }
    }

    override fun onMovieItemThumbnailClick(movie: Movie) {
        navController.navigate(MovieDetailScreen(movie.posterPath, movie.title, movie.overview))
    }

    override fun onSearchQueryUpdate(value: String) {
        viewModelScope.launch {
            if (state.value.movies is Result.Success) {
                state.update { state ->
                    state.copy(
                        movies = Result.Success(
                            (state.movies as Result.Success).data.copy(results = moviesList.filter {
                                it.title.contains(value)
                            })
                        )
                    )
                }
            }
        }
    }

    override fun onRetry() {
        fetchTrendingMoviesData()
    }
}