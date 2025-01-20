package com.example.sportapp.widgets.home

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.sportapp.models.news.domain.NewsListEntity
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.shared.NewsCard

@Composable
fun NewsCardRow(
//    newsList: List<NewsEntity>,
    navController: NavHostController,
    newsViewModel: NewsActivityViewModel,
    news: NewsListEntity,
) {
    val lazyRowState = rememberLazyListState()


    val isAtEnd = remember {
        derivedStateOf {
            val lastVisibleIndex = lazyRowState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
            lastVisibleIndex >= newsViewModel.listOfLoadedNews.size - 1
        }
    }

    LaunchedEffect(isAtEnd.value) {
        if (isAtEnd.value && news.pageNumber < news.totalPages) {
            newsViewModel.loadNewsData(news.pageNumber + 1)
        }
    }

    LazyRow(state = lazyRowState) {
        items( newsViewModel.listOfLoadedNews.size) { index ->
            NewsCard( newsViewModel.listOfLoadedNews[index], navController)
        }
    }
}

