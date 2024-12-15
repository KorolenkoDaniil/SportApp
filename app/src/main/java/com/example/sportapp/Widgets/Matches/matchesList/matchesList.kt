package com.example.sportapp.widgets.matches.matchesList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sportapp.domain.MatchDayEntity
import com.example.sportapp.widgets.matches.matchesList.matchesCard.MatchCard

@Composable
fun MatchesList(
    pageState: PagerState,
    matchDays: List<MatchDayEntity>
) {

    HorizontalPager(
        state = pageState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = matchDays[page].name)
            LazyColumn {
                itemsIndexed(matchDays[pageState.currentPage].matches) { index, item ->
                    MatchCard(item.teamAName, item.teamBName, item.date)
                }
            }

        }
    }
}