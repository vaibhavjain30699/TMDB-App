package com.vaibhav.tmdbapp.navigation

import kotlinx.serialization.Serializable

@Serializable
object MovieScreen

@Serializable
data class MovieDetailScreen(
    val imageURL: String,
    val title: String,
    val overview: String,
)