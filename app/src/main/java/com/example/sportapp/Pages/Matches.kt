package com.example.sportapp.pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportapp.api.viewModels.MatchState
import com.example.sportapp.api.viewModels.MatchesActivityViewModel
import com.example.sportapp.api.viewModels.RankingsActivityViewModel
import com.example.sportapp.shared.CommonError
import com.example.sportapp.shared.Loading
import com.example.sportapp.widgets.matches.calendar.CalendarTab
import kotlinx.coroutines.launch


@Composable
fun MatchesPage(
    mainViewModel: MatchesActivityViewModel = viewModel(),
    rankingsViewModel: RankingsActivityViewModel = viewModel()
) {

    val state by mainViewModel.state.collectAsState()

    Column {
        when (val data = state) {

            is MatchState.Error -> {
                CommonError(mainViewModel)
            }


            MatchState.Load -> {
                Loading()
            }


            is MatchState.MatchContent -> {
                val pageState = rememberPagerState (pageCount = { data.matchDays.size })

                Log.d("daa", pageState.pageCount.toString())

                CalendarTab(pageState, data)

                Spacer(Modifier.height(16.dp))

                HorizontalPager(
                    state = pageState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->


                    Column (
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = data.matchDays[page].name)
                    }
                }
            }
        }
    }
}