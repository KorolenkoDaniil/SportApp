package com.example.sportapp.widgets.matches.matchesList.matchesCard.cardParts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ImagePart (url: String?){
    val painter = rememberAsyncImagePainter(url)

    Image(
        painter = painter,
        contentDescription = "",
        modifier = Modifier.height(50.dp).width(50.dp),
    )
}