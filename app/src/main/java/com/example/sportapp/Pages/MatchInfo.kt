@file:Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")

package com.example.sportapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportapp.api.viewModels.MatchReportActivityViewModel
import com.example.sportapp.api.viewModels.MatchReportState
import com.example.sportapp.api.viewModels.MatchesActivityViewModel
import com.example.sportapp.domain.MatchDayEntity
import com.example.sportapp.domain.MatchEntity
import com.example.sportapp.shared.CommonError

@Composable
fun MatchInfo(
    matchId: String?,
    listDayEntity: List<MatchDayEntity>,
    pageNumber: Int?,
    matchReportViewModel: MatchReportActivityViewModel = viewModel(),
) {

    val match: MatchEntity?
    val matchReportState by matchReportViewModel.getState().collectAsState()


    when (matchReportState) {
        is MatchReportState.RankingsContent -> {
            if (pageNumber != null && pageNumber >= 0 && pageNumber < listDayEntity.size && matchId != null) {

                val dayEntityMatches = listDayEntity[pageNumber].matches

                match = dayEntityMatches.find { it.matchId == matchId }

                matchReportViewModel.loadMatchReport(match!!.matchId)

                Column {
                    Text(text = matchId.toString())
                    Text(text = "page $pageNumber")
                    Text(text = match.teamAName)
                    Text(text = match.teamBName)
                }
            }
        }


        is MatchReportState.Error -> {
            CommonError()
        }



        is MatchReportState.Load -> TODO()
    }


}
