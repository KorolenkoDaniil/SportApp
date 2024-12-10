package com.example.sportapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportapp.API.entities.MatchState
import com.example.sportapp.API.entities.MatchesActivityViewModel

@Composable
fun MatchesPage(
    mainViewModel: MatchesActivityViewModel = viewModel()
) {
//    val coroutineScope = rememberCoroutineScope()

    val state by mainViewModel.state.collectAsState()
    when (val data = state) {
        is MatchState.Error -> {
            Text(text = "Ошибка")
        }
        MatchState.Load -> {
            Text(text = "Загрузка")
        }
        is MatchState.MatchContent -> {
            Column {
                Text(text = data.matches.size.toString())
            }
        }
    }


}
