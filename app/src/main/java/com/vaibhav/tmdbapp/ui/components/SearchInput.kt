package com.vaibhav.tmdbapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vaibhav.tmdbapp.R

@Composable
fun SearchInput(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
) {
    val textFieldState = remember { mutableStateOf(searchQuery) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(0xffe3e8ef),
                shape = RoundedCornerShape(dimensionResource(R.dimen.spacing_8))
            ),
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = textFieldState.value,
            onValueChange = {
                textFieldState.value = it
                onSearchQueryChange(it)
            },
            singleLine = true,
        ) { innerTextField ->
            Row(
                modifier = Modifier.padding(dimensionResource(R.dimen.spacing_16)),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color(0xff9aa4b2)
                )
                Spacer(Modifier.width(dimensionResource(R.dimen.spacing_12)))
                if (textFieldState.value.isEmpty()) {
                    Text(
                        text = "Search Movies",
                        style = TextStyle(
                            color = Color(0xff9aa4b2),
                            fontSize = 16.sp
                        )
                    )
                } else {
                    innerTextField()
                }
            }
        }
    }
}