package com.example.sportapp.presentation.widgets.screens.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sportapp.containers.StatesContainer
import com.example.sportapp.containers.ViewModelContainer
import com.example.sportapp.models.viewModels.NewsState
import com.example.sportapp.models.viewModels.OneNewsActivityViewModel
import com.example.sportapp.models.viewModels.OneNewsSate
import com.example.sportapp.presentation.navigation.Screen
import com.example.sportapp.presentation.widgets.common.shared.CommonError
import com.example.sportapp.presentation.widgets.common.shared.Loading
import com.example.sportapp.presentation.widgets.screens.home.home.newsPageWidgets.NewsPageContent

@Composable
fun NewsPage(
    viewModels: ViewModelContainer,
    states: StatesContainer,
    newsDateTime: String,
    navController: NavHostController,
    horizontalPaddings: Dp,
    showBar: MutableState<Boolean>
) {
    val oneNewsViewModel: OneNewsActivityViewModel = viewModel()
    oneNewsViewModel.loadOneNewsData(newsDateTime)
    val oneNewsState by oneNewsViewModel.getState().collectAsState()
    viewModels.appActivity.changePageName("One News")

    when (oneNewsState) {
        is OneNewsSate.OneNewsContent -> {
            when (states.newsState) {
                is NewsState.NewsContent -> {

                    NewsPageContent(
                        oneNewsState = oneNewsState,
                        navController = navController,
                        newsViewModel = viewModels.newsViewModel,
                        newsList = states.newsState.news,
                        horizontalPaddings,
                        viewModels.authViewModel,
                        showBar
                    )
                }

                is NewsState.Error -> {
                    Log.d("tttNews", "ошибка newsPage")
                    CommonError(viewModels.newsViewModel, Screen.News.route, navController)
                }

                is NewsState.Load -> {
                    Loading()
                }
            }
        }

        is OneNewsSate.Error -> {
            Log.d("newsPage", newsDateTime)
            CommonError(oneNewsViewModel, Screen.News.route, navController)
        }

        is OneNewsSate.Load -> Loading()
    }
}