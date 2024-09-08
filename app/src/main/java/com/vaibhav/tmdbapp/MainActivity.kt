package com.vaibhav.tmdbapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.vaibhav.tmdbapp.data.MovieRepositoryImpl
import com.vaibhav.tmdbapp.data.RetrofitInstance
import com.vaibhav.tmdbapp.ui.MovieScreen
import com.vaibhav.tmdbapp.ui.theme.TMDBAppTheme
import com.vaibhav.tmdbapp.viewmodel.MovieViewModel
import com.vaibhav.tmdbapp.viewmodel.MoviesViewModelFactory

class MainActivity : ComponentActivity() {

    private val repository = MovieRepositoryImpl(RetrofitInstance)

    private val movieViewModel: MovieViewModel by viewModels {
        MoviesViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBAppTheme { MovieScreen(movieViewModel) }
        }
    }
}