package com.example.sportapp.Pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sportapp.Widgets.Home.CurrentMatch
import com.example.sportapp.Widgets.Home.NewsCards
import com.example.sportapp.ui.theme.style1

@Composable
fun HomePage() {
    LazyColumn {
        item { CurrentMatch() }
        item { Spacer(modifier = Modifier.height(32.dp) ) }
        item { Text(text = "Sport news", style = style1) }
        item { Spacer(modifier = Modifier.height(24.dp) ) }
        item { NewsCards() } // news block
        item { Spacer(modifier = Modifier.height(24.dp) ) }
        item { Text(text = "Highlights", style = style1) }
        item { Spacer(modifier = Modifier.height(24.dp) ) }
        item { NewsCards() } // Video block
        item { Spacer(modifier = Modifier.height(24.dp) ) }
    }
}