package com.vaibhav.tmdbapp.data

import com.vaibhav.tmdbapp.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface MovieRepository {
    fun fetchTrendingMovies(): Flow<Result<MovieResponse>>
}

class MovieRepositoryImpl(
    private val retrofit: RetrofitInstance,
) : MovieRepository {

    override fun fetchTrendingMovies(): Flow<Result<MovieResponse>> = flow {
        emit(Result.Loading)
        try {
            val response = retrofit.moviesAPI.getTrendingMovies(BuildConfig.API_KEY)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }.flowOn(Dispatchers.IO)
}