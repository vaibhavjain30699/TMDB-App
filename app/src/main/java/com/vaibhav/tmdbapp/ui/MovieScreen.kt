package com.vaibhav.tmdbapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vaibhav.tmdbapp.data.Movie
import com.vaibhav.tmdbapp.ui.components.MovieItemThumbnail
import com.vaibhav.tmdbapp.ui.components.SearchInput

@Composable
fun MovieScreen(
    moviesList: List<Movie>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 24.dp,
                start = 16.dp,
                end = 16.dp,
            )
    ) {
        SearchInput()
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(count = moviesList.size) { index ->
                MovieItemThumbnail(moviesList[index])
            }
        }
    }
}




