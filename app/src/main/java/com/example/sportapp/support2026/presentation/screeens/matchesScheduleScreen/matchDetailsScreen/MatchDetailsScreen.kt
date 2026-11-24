package com.example.sportapp.support2026.presentation.screeens.matchesScheduleScreen.matchDetailsScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.sportapp.support2026.features.soccer.viewModel.MatchDetailsState
import com.example.sportapp.support2026.features.soccer.viewModel.SoccerMatchDetailsViewModel
import com.example.sportapp.support2026.presentation.screeens.ScreenWrapper

@Composable
fun MatchDetailsScreen (
    matchId: Int?,
    matchDetailsViewModel: SoccerMatchDetailsViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        matchDetailsViewModel.getMatchById(matchId)
    }

    val matchDetailsState by matchDetailsViewModel.matchUiState.collectAsState()


    ScreenWrapper(
        content = {

            when (val state = matchDetailsState) {

                is MatchDetailsState.Load -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    }
                }

                is MatchDetailsState.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Ошибка загрузки данных матча",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                is MatchDetailsState.MatchContent -> {

                    val context = LocalContext.current
                    val match = state.match

                    val teamName1: String = match?.team1?.teamName.toString()
                    val teamName2: String = match?.team2?.teamName.toString()

                    val logo1Painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(context)
                            .data(match?.team1?.teamIconUrl)
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
                            .data(match?.team2?.teamIconUrl)
                            .crossfade(true)
                            .listener(
                                onError = { request, result ->
                                    Log.d("CoilLogo", "Failed URL: ${request.data}", result.throwable)
                                }
                            )
                            .build()
                    )


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = teamName1 ,
                            style = MaterialTheme.typography.bodyLarge,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )

//        if

                        Image(
                            painter = logo1Painter,
                            contentDescription = "team1Logo",
                            modifier = Modifier.size(48.dp),
                            contentScale = ContentScale.Fit
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Image(
                            painter = logo2Painter,
                            contentDescription = "team2Logo",
                            modifier = Modifier.size(48.dp),
                            contentScale = ContentScale.Fit
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = teamName2,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.End,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

            }
        }
    )

}
