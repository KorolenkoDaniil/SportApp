package com.example.sportapp.pagesAndWidgets.widgets.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsListEntity
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.pagesAndWidgets.widgets.shared.NewsCard

@Composable
fun NewsCardRow(
    navController: NavHostController,
    newsViewModel: NewsActivityViewModel,
    news: NewsListEntity,
    horizontalPaddings: Dp
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


    LazyRow(modifier = Modifier.padding(start = horizontalPaddings), state = lazyRowState) {
        items( newsViewModel.listOfLoadedNews.size) { index ->
            NewsCard( newsViewModel.listOfLoadedNews[index], navController)
        }
    }
}

