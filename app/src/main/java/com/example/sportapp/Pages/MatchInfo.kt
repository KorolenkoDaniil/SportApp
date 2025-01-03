package com.example.sportapp.pages

import MatchInfoContent
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.sportapp.api.viewModels.MatchActivityViewModel
import com.example.sportapp.api.viewModels.MatchReportActivityViewModel
import com.example.sportapp.api.viewModels.MatchReportState
import com.example.sportapp.api.viewModels.MatchState
import com.example.sportapp.domain.MatchDayEntity
import com.example.sportapp.shared.CommonError
import com.example.sportapp.shared.Loading


@Composable
fun MatchInfo(
    matchId: String?,
    listDayEntity: List<MatchDayEntity>,
    pageNumber: Int?,
    matchReportViewModel: MatchReportActivityViewModel,
    matchViewModel: MatchActivityViewModel
) {
    val matchReportState by matchReportViewModel.getState().collectAsState()
    val matchState by matchViewModel.getState().collectAsState()

    when (matchReportState) {
        is MatchReportState.RankingsContent -> {
            when (matchState) {
                is MatchState.MatchContent -> {
                    if (pageNumber == null || matchId == null || pageNumber !in listDayEntity.indices) {
                        Log.e(
                            "tttDebug",
                            "Некорректные данные: pageNumber=$pageNumber, matchId=$matchId"
                        )
                        return
                    }

                    val dayEntityMatches = listDayEntity[pageNumber].matches
                    val matchDay = dayEntityMatches.find { it.matchId == matchId }

                    MatchInfoContent(matchDay)
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
