package com.example.sportapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.sportapp.API.ViewModels.MatchState
import com.example.sportapp.API.ViewModels.MatchesActivityViewModel
import com.example.sportapp.widgets.Matches.calendar.Calendar

@Composable
fun MatchesPage(
    mainViewModel: MatchesActivityViewModel
) {

    val state by mainViewModel.state.collectAsState()

    Column ()
    {
        Calendar(mainViewModel)

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



}
