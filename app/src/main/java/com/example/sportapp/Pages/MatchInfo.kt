package com.example.sportapp.pages

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.sportapp.api.viewModels.MatchReportActivityViewModel
import com.example.sportapp.api.viewModels.MatchReportState
import com.example.sportapp.domain.MatchDayEntity
import com.example.sportapp.domain.MatchEntity
import com.example.sportapp.shared.CommonError
import com.example.sportapp.shared.Loading

@Composable
fun MatchInfo(
    matchId: String?,
    listDayEntity: List<MatchDayEntity>,
    pageNumber: Int?,
    matchReportViewModel: MatchReportActivityViewModel
) {

    val match: MatchEntity?
    val matchReportState by matchReportViewModel.getState().collectAsState()


    when (matchReportState) {
        is MatchReportState.RankingsContent -> {

            Log.d("tttMatchReport", "пришли данные")

            if (pageNumber != null && pageNumber >= 0 && pageNumber < listDayEntity.size && matchId != null) {

                val dayEntityMatches = listDayEntity[pageNumber].matches

                match = dayEntityMatches.find { it.matchId == matchId }

                Log.d("tttDebug", "MatchInfo: перед вызовом loadMatchReport")

                Column {
                    Text(text = matchId.toString())
                    Text(text = "page $pageNumber")
                    Text(text = match!!.teamAName)
                    Text(text = match.teamBName)
                }
            }
        }


        is MatchReportState.Error -> {
//            Log.d("tttMatchReport", )
            CommonError(matchReportViewModel)
        }



        is MatchReportState.Load -> {
            Log.d("tttMatchReport", "загрузка репортов матча")
            Loading()
        }
    }


}
