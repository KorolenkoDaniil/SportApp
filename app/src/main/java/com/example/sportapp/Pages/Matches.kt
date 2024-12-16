package com.example.sportapp.pages

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportapp.api.viewModels.MatchState
import com.example.sportapp.api.viewModels.MatchesActivityViewModel
import com.example.sportapp.api.viewModels.RankingsActivityViewModel
import com.example.sportapp.api.viewModels.RankingsState
import com.example.sportapp.shared.CommonError
import com.example.sportapp.shared.Loading
import com.example.sportapp.widgets.matches.calendar.CalendarTab
import com.example.sportapp.widgets.matches.matchesList.MatchesList

@Composable
fun MatchesPage(
    mainViewModel: MatchesActivityViewModel = viewModel(),
    rankingsViewModel: RankingsActivityViewModel = viewModel()
) {
    val state by mainViewModel.state.collectAsState()
    val rankingsState by rankingsViewModel.state.collectAsState()

    Column {
        when (val matchData = state) {
            is MatchState.Error -> {
                Log.d("stateee", rankingsState.toString())
                CommonError(mainViewModel, "1")
            }

            MatchState.Load -> {
                Log.d("stateee", rankingsState.toString())
                Loading()
            }

            is MatchState.MatchContent -> {
                when (val rankingData = rankingsState) {
                    is RankingsState.RankingsContent -> {

                        val pageState = rememberPagerState(pageCount = { matchData.matchDays.size })

                        Log.d("daa", pageState.pageCount.toString())

                        CalendarTab(pageState, matchData)

                        Spacer(Modifier.height(16.dp))

                        MatchesList(
                            pageState = pageState,
                            matchDays = matchData.matchDays,
                            rankings = rankingData.rankings
                        )
                    }

                    RankingsState.Load -> {
                        Loading()
                    }

                    is RankingsState.Error -> {
                        CommonError(mainViewModel, "2")
                    }
                }
            }
        }
    }
}
