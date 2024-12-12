package com.example.sportapp.widgets.matches.matchesList

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.example.sportapp.api.entities.matches.MatchDayEntity
import com.example.sportapp.widgets.matches.matchesList.matchesCard.MatchCard

@Composable
fun MatchesList (
    matchDay: MatchDayEntity
){
    LazyColumn {
        itemsIndexed(matchDay.matches) { index, item ->
            MatchCard(item.teamAName, item.teamBName, item.date)
        }
    }
}