package com.example.sportapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportapp.api.viewModels.MatchState
import com.example.sportapp.api.viewModels.MatchesActivityViewModel
import com.example.sportapp.api.viewModels.RankingsActivityViewModel
import com.example.sportapp.shared.CommonError
import com.example.sportapp.shared.Loading
import com.example.sportapp.widgets.matches.calendar.Calendar
import com.example.sportapp.widgets.matches.matchesList.MatchesList

@Composable
fun MatchesPage(
    mainViewModel: MatchesActivityViewModel = viewModel(),
    rankingsViewModel: RankingsActivityViewModel = viewModel()
) {

    val state by mainViewModel.state.collectAsState()
    val state1 by rankingsViewModel.state.collectAsState()

    Column() {
        when (val data = state) {
            is MatchState.Error -> {
                CommonError(mainViewModel)
            }

            MatchState.Load -> {
                Loading()
            }

            is MatchState.MatchContent -> {
                Calendar(data)
                MatchesList(data.matchDays[0])
            }
        }
    }
}
