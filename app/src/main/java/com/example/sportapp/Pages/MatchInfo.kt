package com.example.sportapp.pages

import AppActivityViewModel
import MatchInfoContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.sportapp.api.viewModels.MatchActivityViewModel
import com.example.sportapp.api.viewModels.MatchReportActivityViewModel
import com.example.sportapp.api.viewModels.MatchReportState
import com.example.sportapp.api.viewModels.MatchState
import com.example.sportapp.api.viewModels.MatchesActivityViewModel
import com.example.sportapp.shared.CommonError
import com.example.sportapp.shared.Loading


@Composable
fun MatchInfo(
    matchReportViewModel: MatchReportActivityViewModel,
    matchViewModel: MatchActivityViewModel,
    matchesViewModel: MatchesActivityViewModel,
    appActivity: AppActivityViewModel,
) {
    appActivity.changePageName("Match center")
    val matchReportState by matchReportViewModel.getState().collectAsState()
    val matchState by matchViewModel.getState().collectAsState()

    when (matchReportState) {
        is MatchReportState.RankingsContent -> {
            when (matchState) {
                is MatchState.MatchContent -> {

                    MatchInfoContent(matchesViewModel, matchReportState.rankings)
                }

                is MatchState.Error -> {
                    CommonError(matchViewModel)
                }

                is MatchState.Load -> {
                    Loading()
                }
            }
        }

        is MatchReportState.Error -> {
            CommonError(matchReportViewModel)
        }

        is MatchReportState.Load -> {
            Loading()
        }
    }
}
