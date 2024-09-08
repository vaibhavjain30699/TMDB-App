package com.vaibhav.tmdbapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vaibhav.tmdbapp.data.Result
import com.vaibhav.tmdbapp.ui.components.MovieItemThumbnail
import com.vaibhav.tmdbapp.ui.components.SearchInput
import com.vaibhav.tmdbapp.viewmodel.MovieViewModel

@Composable
fun MovieScreen(
    viewModel: MovieViewModel
) {
    val state = viewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 24.dp,
                start = 16.dp,
                end = 16.dp,
            ),
    ) {
        SearchInput(viewModel::onSearchQueryUpdate)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            state.value.movies?.let {
                when (it) {
                    is Result.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .height(32.dp)
                                .width(32.dp)
                        )
                    }

                    is Result.Success -> {
                        LazyVerticalGrid(
                            modifier = Modifier.fillMaxSize(),
                            columns = GridCells.Fixed(count = 2),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            val list = it.data.results
                            items(count = list.size) { index ->
                                MovieItemThumbnail(
                                    list[index],
                                    viewModel::onMovieItemThumbnailClick
                                )
                            }
                        }
                    }

                    is Result.Error -> {
                        ElevatedButton(
                            onClick = viewModel::onRetry,
                            content = {
                                Text("Error")
                            }
                        )
                    }
                }
            }
        }
    }
}



