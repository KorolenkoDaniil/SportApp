package com.example.sportapp.pagesAndWidgets.pages

import AppActivityViewModel
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.models.viewModels.NewsState
import com.example.sportapp.models.viewModels.OneNewsActivityViewModel
import com.example.sportapp.models.viewModels.OneNewsSate
import com.example.sportapp.pagesAndWidgets.widgets.home.NewsPageContent
import com.example.sportapp.pagesAndWidgets.widgets.shared.CommonError
import com.example.sportapp.pagesAndWidgets.widgets.shared.Loading

@Composable
fun NewsPage(
    appActivity: AppActivityViewModel,
    newsDateTime: String,
    newsState: NewsState,
    navController: NavHostController,
    newsViewModel: NewsActivityViewModel,
    horizontalPaddings: Dp
) {
    val oneNewsViewModel: OneNewsActivityViewModel = viewModel()
    oneNewsViewModel.loadOneNewsData(newsDateTime)
    val oneNewsState by oneNewsViewModel.getState().collectAsState()
    appActivity.changePageName("One News")

    when (oneNewsState) {
        is OneNewsSate.OneNewsContent -> {
            when (newsState) {
                is NewsState.NewsContent -> {
                    Column(Modifier.fillMaxSize()) { // Используем Column вместо LazyColumn
                        NewsPageContent(
                            oneNewsState = oneNewsState,
                            navController = navController,
                            newsViewModel = newsViewModel,
                            newsList = newsState.news,
                            horizontalPaddings
                        )
                    }
                }
                is NewsState.Error -> {
                    Log.d("tttNews", "ошибка newsPage")
                    CommonError(newsViewModel, Screen.News.route, navController)
                }
                is NewsState.Load -> {
                    Loading()
                }
            }
        }
        is OneNewsSate.Error -> CommonError(oneNewsViewModel, Screen.News.route, navController)
        is OneNewsSate.Load -> Loading()
    }
}