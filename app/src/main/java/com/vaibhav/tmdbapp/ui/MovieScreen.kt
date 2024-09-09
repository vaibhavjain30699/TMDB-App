package com.vaibhav.tmdbapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.vaibhav.tmdbapp.R
import com.vaibhav.tmdbapp.data.Movie
import com.vaibhav.tmdbapp.data.Result
import com.vaibhav.tmdbapp.ui.components.EmptyState
import com.vaibhav.tmdbapp.ui.components.ErrorState
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
                top = dimensionResource(R.dimen.spacing_24),
                start = dimensionResource(R.dimen.spacing_16),
                end = dimensionResource(R.dimen.spacing_16),
            ),
    ) {
        SearchInput(
            searchQuery = state.value.searchQuery,
            onSearchQueryChange = viewModel::onSearchQueryUpdate
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.spacing_24)))
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
                                .height(dimensionResource(R.dimen.spacing_32))
                                .width(dimensionResource(R.dimen.spacing_32))
                        )
                    }

                    is Result.Success -> {
                        val list = it.data.results
                        if (list.isEmpty()) {
                            EmptyState()
                        } else {
                            ItemGrid(
                                list = list,
                                onThumbnailClick = viewModel::onMovieItemThumbnailClick,
                            )
                        }
                    }

                    is Result.Error -> {
                        ErrorState(onRetry = viewModel::onRetry)
                    }
                }
            }
        }
    }
}

@Composable
fun ItemGrid(
    list: List<Movie>,
    onThumbnailClick: (Movie) -> Unit,
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(count = 2),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_16)),
    ) {
        items(count = list.size) { index ->
            MovieItemThumbnail(
                list[index],
                onThumbnailClick
            )
        }
    }
}




