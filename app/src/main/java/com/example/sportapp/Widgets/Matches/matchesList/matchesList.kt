package com.example.sportapp.widgets.matches.matchesList

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.sportapp.domain.MatchDayEntity
import com.example.sportapp.domain.RankingEntity
import com.example.sportapp.widgets.matches.matchesList.matchesCard.MatchCard


@Composable
fun MatchesList(
    pageState: PagerState,
    matchDays: List<MatchDayEntity>,
    rankings: List<RankingEntity>,
    matchesMatchInfoNavController: NavHostController
) {

    val logoUrlMap = rankings.associateBy { it.name }

    HorizontalPager(
        state = pageState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LazyColumn {
                itemsIndexed(matchDays[pageState.currentPage].matches) { index, item ->

                    val teamALogoUrl = logoUrlMap[item.teamAAcronym]?.logoUrl
                    val teamBLogoUrl = logoUrlMap[item.teamBAcronym]?.logoUrl

                    Log.d("ttt", teamALogoUrl.toString())
                    Log.d("ttt", teamBLogoUrl.toString())

                    MatchCard(
                        teamALogoUrl,
                        teamBLogoUrl,
                        item,
                        matchesMatchInfoNavController,
                        page
                    )
                }
            }
        }
    }
}