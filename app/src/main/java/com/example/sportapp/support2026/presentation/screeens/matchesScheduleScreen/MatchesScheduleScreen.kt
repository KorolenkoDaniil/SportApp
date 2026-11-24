package com.example.sportapp.support2026.presentation.screeens.matchesScheduleScreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.sportapp.support2026.features.soccer.viewModel.MatchesListState
import com.example.sportapp.support2026.features.soccer.viewModel.SoccerViewModel
import com.example.sportapp.support2026.presentation.screeens.matchesScheduleScreen.matchItem.MatchItem

@Composable
fun MatchesScheduleScreen(
    navController: NavHostController,
    soccerViewModel: SoccerViewModel = hiltViewModel(),
) {
    val uiState by soccerViewModel.matchesUiState.collectAsState()
    val listState = rememberLazyListState()
    val modifier: Modifier = Modifier
    val context = LocalContext.current

    when (val state = uiState) {
        is MatchesListState.Load -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }
        }

        is MatchesListState.Error -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Ошибка загрузки матчей",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }
        }

        is MatchesListState.MatchesContent -> {
            if (state.matches.isEmpty()) {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Матчей пока нету и не будет",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Матчи лиги ****",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        items(
                            items = state.matches,
                            key = { it.matchID },

                        ) { match ->

                            val logo1Painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(context)
                                    .data(match.team1?.teamIconUrl)
                                    .crossfade(true)
                                    .listener(
                                        onError = { request, result ->
                                            Log.d("CoilLogo", "Failed URL: ${request.data}", result.throwable)
                                        }
                                    )
                                    .build()
                            )

                            val logo2Painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(context)
                                    .data(match.team2?.teamIconUrl)
                                    .crossfade(true)
                                    .listener(
                                        onError = { request, result ->
                                            Log.d("CoilLogo", "Failed URL: ${request.data}", result.throwable)
                                        }
                                    )
                                    .build()
                            )

                            MatchItem(
                                match = match,
                                navController = navController,
                                logo1Painter = logo1Painter,
                                logo2Painter = logo2Painter,
                            )
                        }
                    }
                }
            }
        }
    }
}