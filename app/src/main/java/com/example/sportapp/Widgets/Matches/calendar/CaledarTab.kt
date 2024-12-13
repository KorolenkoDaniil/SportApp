package com.example.sportapp.widgets.matches.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportapp.api.viewModels.MatchState
import com.example.sportapp.ui.theme.Blue100
import kotlinx.coroutines.launch

@Composable
fun CalendarTab(pageState: PagerState, data: MatchState.MatchContent) {
    val scope = rememberCoroutineScope()

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        userScrollEnabled = false
    ) {
        items(data.matchDays.size) { index ->
            val dayName = data.matchDays[index]

            val isSelected = pageState.currentPage == index

            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(
                        color = if (isSelected) Blue100 else Color.Transparent
                    )
            ) {
                Tab(
                    selected = pageState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pageState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(text = dayName.name)
                    }
                )
            }

        }
    }
}