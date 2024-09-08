package com.vaibhav.tmdbapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET("trending/movie/week?")
    suspend fun getTrendingMovies(@Query("api_key") apiKey: String): MovieResponse
}
