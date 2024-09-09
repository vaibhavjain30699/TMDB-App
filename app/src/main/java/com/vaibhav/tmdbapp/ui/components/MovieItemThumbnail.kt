package com.vaibhav.tmdbapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.vaibhav.tmdbapp.R
import com.vaibhav.tmdbapp.data.Movie

@Composable
fun MovieItemThumbnail(
    movie: Movie,
    onItemThumbnailClick: (Movie) -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                onItemThumbnailClick(movie)
            }
    ) {
        Thumbnail(movie.posterPath)
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_12)))
        Text(movie.title)
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_16)))
    }
}