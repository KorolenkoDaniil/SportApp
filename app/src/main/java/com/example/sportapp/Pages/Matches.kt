package com.example.sportapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportapp.api.viewModels.MatchState
import com.example.sportapp.api.viewModels.MatchesActivityViewModel
import com.example.sportapp.api.viewModels.RankingsActivityViewModel
import com.example.sportapp.shared.CommonError
import com.example.sportapp.shared.Loading
import com.example.sportapp.widgets.matches.calendar.Calendar
import com.example.sportapp.widgets.matches.matchesList.MatchesList

@Composable
fun MatchesPage(
    mainViewModel: MatchesActivityViewModel = viewModel(),
    rankingsViewModel: RankingsActivityViewModel = viewModel()
) {
    val state by mainViewModel.state.collectAsState()
    val state1 by rankingsViewModel.state.collectAsState()
    val currentMatchDay = remember { mutableStateOf(0) }

    Column {
        when (val data = state) {
            is MatchState.Error -> {
                CommonError(mainViewModel)
            }

            MatchState.Load -> {
                Loading()
            }

            is MatchState.MatchContent -> {
                val pagerState = rememberPagerState(initialPage = currentMatchDay.value)

                // Синхронизация currentMatchDay и pagerState
                LaunchedEffect(pagerState.currentPage) {
                    if (currentMatchDay.value != pagerState.currentPage) {
                        currentMatchDay.value = pagerState.currentPage
                        // Вы можете вызвать mainViewModel.setOpenedMatchDay здесь, если это необходимо
                        // mainViewModel.setOpenedMatchDay(pagerState.currentPage)
                    }
                }

                Calendar(data, currentMatchDay.value)

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { pageIndex ->
                    Column {
                        MatchesList(data.matchDays[pageIndex])
                    }
                }
            }
        }
    }
}
