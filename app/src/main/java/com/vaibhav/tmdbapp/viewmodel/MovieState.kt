package com.vaibhav.tmdbapp.viewmodel

import com.vaibhav.tmdbapp.data.MovieResponse
import com.vaibhav.tmdbapp.data.Result

data class MoviesState(
    val movies: Result<MovieResponse>? = null,
)
