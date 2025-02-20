package com.example.sportapp.pages

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.models.user.domain.UserEntity
import com.example.sportapp.models.viewModels.MatchesActivitySoccerViewModel
import com.example.sportapp.models.viewModels.MatchesState
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.models.viewModels.NewsSate
import com.example.sportapp.models.viewModels.VideosState
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
import com.example.sportapp.shared.CommonError
import com.example.sportapp.shared.Loading
import com.example.sportapp.shared.SearchLine
import com.example.sportapp.ui.theme.style1
import com.example.sportapp.widgets.home.CurrentMatch
import com.example.sportapp.widgets.home.NewsCardRow
import com.example.sportapp.widgets.home.VideoCardRow
import kotlinx.coroutines.flow.StateFlow


@Composable
fun HomePage(
    newsState: NewsSate,
    state: MatchesState,
    videoState: VideosState,
    newsViewModel: NewsActivityViewModel,
    matchesViewModel: MatchesActivitySoccerViewModel,
    videoViewModel: YoutubeActivityViewModel,
    navController: NavHostController,
    user: StateFlow<UserEntity?>
) {
    when (state) {

        is MatchesState.MatchesContent -> {

            when (videoState) {

                is VideosState.VideosContent -> {

                    when (newsState) {

                        is NewsSate.NewsContent -> {

                            LazyColumn {
                                item { SearchLine(user)}
                                item { Spacer(modifier = Modifier.height(32.dp)) }
                                item { CurrentMatch(matchesViewModel.nearestMatch) }
                                item { Spacer(modifier = Modifier.height(32.dp)) }
                                item { Text(text = "Sport news", style = style1) }
                                item { Spacer(modifier = Modifier.height(24.dp)) }
                                item { NewsCardRow(navController, newsViewModel, newsState.news) }
                                item { Spacer(modifier = Modifier.height(24.dp)) }
                                item { Text(text = "Highlights", style = style1) }
                                item { Spacer(modifier = Modifier.height(24.dp)) }
                                item { VideoCardRow(videoState.videos.items) }
                                item { Spacer(modifier = Modifier.height(24.dp)) }
                            }
                        }

                        is NewsSate.Error -> {
                            Log.d("tttNews", "ошибка Home")
                            CommonError(newsViewModel, "ошибка загрузки новостей")
                        }

                        is NewsSate.Load -> {
                            Loading()
                        }
                    }
                }

                is VideosState.Error ->
                    CommonError(videoViewModel, "ошибка загрузки видео")


                is VideosState.Load -> Loading()
            }
        }

        //ошибька загрузки матчей
        is MatchesState.Error -> {
            CommonError(matchesViewModel, "ошибка загрузки матчей")
        }

        //загрузка матчей
        MatchesState.Load -> {
            Loading()
        }
    }
}

