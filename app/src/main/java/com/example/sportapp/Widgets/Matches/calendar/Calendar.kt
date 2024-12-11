package com.example.sportapp.widgets.Matches.calendar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportapp.API.ViewModels.MatchesActivityViewModel
import com.example.sportapp.ui.theme.style7


@Composable
fun Calendar(
    mainViewModel: MatchesActivityViewModel
    ) {

    val days by mainViewModel.matchDays.collectAsState()
    Log.d("ttt", days.toString())

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
        ){
            itemsIndexed(days) { index, item ->
                CalendarDay(item.name, style7)
            }

        }
    }
}