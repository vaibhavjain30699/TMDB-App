package com.vaibhav.tmdbapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.vaibhav.tmdbapp.data.Movie

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieItemThumbnail(
    movie: Movie,
    onItemThumbnailClick: (Movie) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 30.dp)
            .clickable {
                onItemThumbnailClick(movie)
            }
    ) {
        val imageURL = "https://image.tmdb.org/t/p/original" + movie.posterPath
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.0f)
                .defaultMinSize(1.dp),
            painter = rememberImagePainter(
                data = imageURL
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(movie.title)
    }
}