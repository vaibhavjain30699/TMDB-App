package com.vaibhav.tmdbapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.vaibhav.tmdbapp.data.MovieRepositoryImpl
import com.vaibhav.tmdbapp.data.RetrofitInstance
import com.vaibhav.tmdbapp.navigation.MovieDetailScreen
import com.vaibhav.tmdbapp.navigation.MovieScreen
import com.vaibhav.tmdbapp.ui.MovieDetailScreen
import com.vaibhav.tmdbapp.ui.MovieScreen
import com.vaibhav.tmdbapp.ui.theme.TMDBAppTheme
import com.vaibhav.tmdbapp.viewmodel.MovieViewModel
import com.vaibhav.tmdbapp.viewmodel.MoviesViewModelFactory

class MainActivity : ComponentActivity() {

    private val repository = MovieRepositoryImpl(RetrofitInstance)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBAppTheme {
                val navController = rememberNavController()
                val movieViewModel: MovieViewModel by viewModels {
                    MoviesViewModelFactory(repository, navController)
                }
                NavHost(
                    navController = navController,
                    startDestination = MovieScreen
                ) {
                    composable<MovieScreen> {
                        MovieScreen(movieViewModel)
                    }

                    composable<MovieDetailScreen> {
                        val args = it.toRoute<MovieDetailScreen>()
                        MovieDetailScreen(
                            Triple(
                                args.imageURL,
                                args.title,
                                args.overview
                            )
                        )
                    }
                }

            }
        }
    }
}