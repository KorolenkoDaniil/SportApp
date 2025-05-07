package com.example.sportapp.presentation.widgets.screens.home.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.presentation.widgets.common.shared.NewsCard
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewsCardRow(
    navController: NavHostController,
    newsViewModel: NewsActivityViewModel,
    horizontalPaddings: Dp,
) {

    val listState = rememberLazyListState()

    LaunchedEffect (Unit) {
        if (newsViewModel.newsList.isEmpty()){
            newsViewModel.loading.value = true
            newsViewModel.searchAndSetNews(
                pageNumber = newsViewModel.page.value,
                searchPrompt = "",
                sportIndex = -1,
                itemList = newsViewModel.newsList,
                clearElements = false
            )
            newsViewModel.loading.value = false
        }
    }

    LaunchedEffect (listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collectLatest {  index ->
                if (!newsViewModel.loading.value && index != null && index >= newsViewModel.newsList.size - 5){
                    newsViewModel.page.value++
                    newsViewModel.loading.value = true
                    newsViewModel.searchAndSetNews(
                        pageNumber = newsViewModel.page.value,
                        searchPrompt = "",
                        sportIndex = -1,
                        itemList = newsViewModel.newsList,
                        clearElements = false
                    )
                    newsViewModel.loading.value = false
                }
            }
    }


    LazyRow(state = listState, modifier = Modifier.padding(start = horizontalPaddings)) {
        items(newsViewModel.newsList.size){ index ->

            val news = newsViewModel.newsList[index]

            NewsCard(
                news = news,
                navController = navController
            )
        }
        item {
            if (newsViewModel.loading.value) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.height(50.dp))
                }
            }
        }
    }
}

