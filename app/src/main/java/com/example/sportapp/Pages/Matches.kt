package com.example.sportapp.Pages

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.sportapp.API.Entities.fetchMatches
import kotlinx.coroutines.launch

@Composable
fun MatchesPage() {
    val coroutineScope = rememberCoroutineScope()

    Button(
        onClick = {
            coroutineScope.launch {
                fetchMatches()
            }
        }
    ) {
        Text(text = "Кнопка")
    }
}
