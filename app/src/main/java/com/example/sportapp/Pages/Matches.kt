package com.example.sportapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
    val currentPage by mainViewModel.openedMatchDay.collectAsState()

    Column {
        when (val data = state) {
            is MatchState.Error -> {
                CommonError(mainViewModel)
            }

            MatchState.Load -> {
                Loading()
            }

            is MatchState.MatchContent -> {
                val pagerState = rememberPagerState(initialPage = currentPage, pageCount = { data.matchDays.size })

                LaunchedEffect(pagerState.currentPage) {
                    if (pagerState.currentPage != currentPage) {
                        mainViewModel.changeMatchDay(pagerState.currentPage)
                    }
                }

                LaunchedEffect(currentPage) {
                    if (pagerState.currentPage != currentPage) {
                        pagerState.scrollToPage(currentPage)
                    }
                }

                Calendar(data, currentPage)

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


