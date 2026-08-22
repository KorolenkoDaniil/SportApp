package com.example.sportapp.support2026.presentation.screeens.homeScreen.widgets.homeTopBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sportapp.R
import com.example.sportapp.support2026.presentation.screeens.homeScreen.widgets.homeTopBar.searchRow.SearchRow

@Composable
fun HomeTopBar (
    imageUrl: String,
    searchPrompt: String,
    onSearchQueryChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        SearchRow(
            searchPrompt = searchPrompt,
            onSearchQueryChange = onSearchQueryChange
        )

        AsyncImage(
            model = imageUrl,
            contentDescription = "Avatar",
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_launcher_background),
            error = painterResource(R.drawable.ic_launcher_background)
        )
    }
}