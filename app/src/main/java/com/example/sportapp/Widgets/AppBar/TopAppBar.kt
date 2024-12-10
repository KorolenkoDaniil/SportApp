package com.example.sportapp.widgets.AppBar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sportapp.shared.BottomBar.UserImage
import com.example.sportapp.widgets.BottomBar.SearchBar

@Composable
fun TopAppBar(modifier: Modifier){
    Row(modifier = modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchBar(Modifier.weight(1f))
        UserImage()
    }
}