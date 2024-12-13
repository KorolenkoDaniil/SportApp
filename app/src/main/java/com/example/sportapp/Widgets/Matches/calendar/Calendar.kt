package com.example.sportapp.widgets.matches.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportapp.api.viewModels.MatchState
import com.example.sportapp.ui.theme.Blue100
import com.example.sportapp.ui.theme.style6
import com.example.sportapp.ui.theme.style7


@Composable
fun Calendar(
    data: MatchState.MatchContent,
    openedMatchDay: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color.White),
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        ) {
            itemsIndexed(data.matchDays) { index, item ->
                when (index == openedMatchDay){
                    true -> CalendarDay(item.name, style6, backColor = Blue100)
                    false -> CalendarDay(item.name, style7, backColor = Color.Transparent)
                }
            }
        }
    }
}