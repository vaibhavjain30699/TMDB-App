package com.vaibhav.tmdbapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vaibhav.tmdbapp.ui.MovieScreen
import com.vaibhav.tmdbapp.ui.theme.TMDBAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBAppTheme { MovieScreen(emptyList()) }
        }
    }
}