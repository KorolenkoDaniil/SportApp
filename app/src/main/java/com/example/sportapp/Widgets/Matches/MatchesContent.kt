package com.example.sportapp.widgets.matches

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sportapp.api.viewModels.MatchState
import com.example.sportapp.api.viewModels.MatchesActivityViewModel
import com.example.sportapp.api.viewModels.RankingsState
import com.example.sportapp.shared.CommonError
import com.example.sportapp.shared.Loading
import com.example.sportapp.widgets.matches.calendar.CalendarTab
import com.example.sportapp.widgets.matches.matchesList.MatchesList

@SuppressLint("NewApi")
@Composable
fun MatchesContent(
    state: MatchState,
    rankingsState: RankingsState,
    mainViewModel: MatchesActivityViewModel
) {
    when (val matchData = state) {
        is MatchState.Error -> {
            CommonError(mainViewModel, "Error loading matches")
        }

        MatchState.Load -> {
            Loading()
        }

        is MatchState.MatchContent -> {
            when (val rankingData = rankingsState) {
                is RankingsState.RankingsContent -> {
                    val pageState = rememberPagerState(pageCount = { matchData.matchDays.size })

                    Column {
                        CalendarTab(pageState, matchData)
                        Spacer(Modifier.height(16.dp))
                        MatchesList(
                            pageState = pageState,
                            matchDays = matchData.matchDays,
                            rankings = rankingData.rankings
                        )
                    }
                }

                RankingsState.Load -> {
                    Loading()
                }

                is RankingsState.Error -> {
                    CommonError(mainViewModel, "Error loading rankings")
                }
            }
        }
    }
}




