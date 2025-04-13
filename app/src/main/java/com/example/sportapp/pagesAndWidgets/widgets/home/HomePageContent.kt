package com.example.sportapp.pagesAndWidgets.widgets.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsListEntity
import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
import com.example.sportapp.models.viewModels.AuthViewModel
import com.example.sportapp.models.viewModels.MatchesActivitySoccerViewModel
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
import com.example.sportapp.pagesAndWidgets.widgets.shared.SearchLine
import com.example.sportapp.ui.theme.style1
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomePageContent(
    user: StateFlow<UserEntity?>,
    authViewModel: AuthViewModel,
    newsViewModel: NewsActivityViewModel,
    matchesViewModel: MatchesActivitySoccerViewModel,
    navController: NavHostController,
    news: NewsListEntity,
    videoViewModel: YoutubeActivityViewModel,
    horizontalPaddings: Dp
) {
    LazyColumn {
        item { SearchLine(user, authViewModel, navController,  horizontalPaddings) }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { CurrentMatch(matchesViewModel.nearestMatch, horizontalPaddings) }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { Text(text = "Sport news", style = style1, modifier = Modifier.padding(horizontal = horizontalPaddings) ) }
        item { Spacer(modifier = Modifier.height(20.dp)) }
        item { NewsCardRow(navController, newsViewModel, news, horizontalPaddings) }
        item { Spacer(modifier = Modifier.height(20.dp)) }
        item { Text(text = "Recommended videos", style = style1, modifier = Modifier.padding(horizontal = horizontalPaddings) ) }
        item { Spacer(modifier = Modifier.height(20.dp)) }
        item { VideoCardRow( videoViewModel = videoViewModel, navController = navController, horizontalPaddings ) }
        item { Spacer(modifier = Modifier.height(24.dp)) }
    }
}