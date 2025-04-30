package com.example.sportapp.presentation.widgets.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.containers.StatesContainer
import com.example.sportapp.containers.ViewModelContainer
import com.example.sportapp.models.viewModels.NewsState
import com.example.sportapp.models.viewModels.OneNewsActivityViewModel
import com.example.sportapp.models.viewModels.OneNewsSate
import com.example.sportapp.presentation.navigation.Screen
import com.example.sportapp.presentation.widgets.common.shared.CommonError
import com.example.sportapp.presentation.widgets.common.shared.Loading
import com.example.sportapp.presentation.widgets.screens.home.home.newsPageWidgets.NewsPageContent

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NewsPage(
    viewModels: ViewModelContainer,
    states: StatesContainer,
    newsDateTime: String,
    navController: NavHostController,
    horizontalPaddings: Dp,
    showBar: MutableState<Boolean>,
    itemList: SnapshotStateList<NewsEntity>
) {
    val oneNewsViewModel: OneNewsActivityViewModel = viewModel()
    val currentUserEmail = viewModels.authViewModel.currentUser.value?.email

    // Загружаем новость только если email доступен
    LaunchedEffect(newsDateTime, currentUserEmail) {
        if (currentUserEmail != null) {
            oneNewsViewModel.loadOneNewsData(newsDateTime, currentUserEmail)
        } else {
            Log.e("NewsPage", "Email is null, cannot load news")
        }
    }

    val oneNewsState by oneNewsViewModel.getState().collectAsState()
    viewModels.appActivity.changePageName("One News")

    when (val oneNews = oneNewsState) {
        is OneNewsSate.OneNewsContent -> {
            when (states.newsState) {
                is NewsState.NewsContent -> {
                    NewsPageContent(
                        oneNewsState = oneNews,
                        navController = navController,
                        newsViewModel = viewModels.newsViewModel,
                        horizontalPaddings = horizontalPaddings,
                        authModel = viewModels.authViewModel,
                        showBar = showBar,
                        itemList
                    )
                }

                is NewsState.Error -> {
                    Log.d("NewsPage", "Ошибка в NewsState")
                    CommonError(viewModels.newsViewModel, Screen.News.route, navController, "новости")
                }

                is NewsState.Load -> {
                    Loading()
                }
            }
        }

        is OneNewsSate.Error -> {
            Log.d("NewsPage", "Ошибка в OneNewsState. NewsDateTime: $newsDateTime")
            CommonError(oneNewsViewModel, Screen.News.route, navController, "1 новость")
        }

        is OneNewsSate.Load -> {
            Loading()
        }
    }
}
