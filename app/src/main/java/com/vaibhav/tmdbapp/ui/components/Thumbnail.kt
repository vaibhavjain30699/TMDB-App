package com.vaibhav.tmdbapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@OptIn(ExperimentalCoilApi::class)
@Composable
fun Thumbnail(imageUrl: String){
    val imageURL = "https://image.tmdb.org/t/p/original$imageUrl"
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
}