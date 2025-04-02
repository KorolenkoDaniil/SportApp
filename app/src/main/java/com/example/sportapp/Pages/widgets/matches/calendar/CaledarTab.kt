package com.example.sportapp.pages.widgets.matches.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.sportapp.models.viewModels.MatchesState
import com.example.sportapp.ui.theme.Blue100
import kotlinx.coroutines.launch

@Composable
fun CalendarTab(pageState: PagerState, data: MatchesState.MatchesContent) {

    val scope = rememberCoroutineScope()
    val tabState = rememberLazyListState()


    LaunchedEffect(pageState.currentPage) {
        tabState.animateScrollToItem(
            index = pageState.currentPage,
        )
    }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = tabState
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
                    selected = isSelected,
                    onClick = {
                        scope.launch {
                            pageState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(text = dayName.name, style = TextStyle(
                            if (isSelected) Color.White else Color.Black
                        ))
                    }
                )
            }
        }
    }
}
