package com.example.sportapp.pages

import AppActivityViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.sportapp.models.viewModels.MatchesActivitySoccerViewModel
import com.example.sportapp.models.viewModels.MatchesState
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.models.viewModels.NewsSate
import com.example.sportapp.models.viewModels.VideosState
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
import com.example.sportapp.shared.CommonError
import com.example.sportapp.widgets.first_page.FirstPageElements

@Composable
fun FirstPage(
    newsState: NewsSate,
    matchesState: MatchesState,
    videoState: VideosState,
    newsViewModel: NewsActivityViewModel,
    matchesViewModel: MatchesActivitySoccerViewModel,
    videoViewModel: YoutubeActivityViewModel,
    appActivity: AppActivityViewModel,
    navController: NavHostController
) {
    when (newsState) {
        is NewsSate.Load -> {
            FirstPageElements()
        }

        is NewsSate.Error -> CommonError(newsViewModel)
        
        is NewsSate.NewsContent -> {
            
            when (matchesState) {
                is MatchesState.Load -> {
                    FirstPageElements()
                }

                is MatchesState.Error -> CommonError(matchesViewModel)
                
                is MatchesState.MatchesContent -> {

                    when (videoState) {
                        is VideosState.Load -> {
                            FirstPageElements()
                        }

                        is VideosState.Error -> CommonError(videoViewModel)

                        is VideosState.VideosContent -> {
                            appActivity.changeShowBars(true)
                            navController.navigate("home")
                        }
                    }

                }
            }
        }
    }
}
