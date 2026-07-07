package com.example.sportapp.presentation.widgets.screens.home.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sportapp.presentation.widgets.common.shared.NewsCard
import com.example.sportapp.support2026.features.news.presentation.NewsState
import com.example.sportapp.support2026.features.news.presentation.NewsViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewsCardRow(
//    navController: NavHostController,
//    newsViewModel: NewsActivityViewModel,
    newsViewModel: NewsViewModel,
//    horizontalPaddings: Dp,
) {
    //состояние списка UI
    val listState = rememberLazyListState()

    // 1. Подписываемся на состояние из ViewModel
    val newsContentState by newsViewModel.state.collectAsState()
    val newsLoadingState by newsViewModel.loading;

    val newsList = when (newsContentState) {
        is NewsState.NewsContent -> (newsContentState as NewsState.NewsContent).news
        else -> emptyList()
    }

    LaunchedEffect (Unit) {

    }

    LaunchedEffect (listState, newsList) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collectLatest {  index ->
                if (!newsLoadingState && index != null && index >= newsList.size - 4){
                    newsViewModel.loadNews(true )
                }
            }

        if (newsList.isEmpty() && !newsLoadingState) {
            newsViewModel.loadNews(false )
        }

    }


//    LazyRow(state = listState, modifier = Modifier.padding(start = horizontalPaddings)) {
//        items(newsViewModel.newsList.size){ index ->
//
//            val news = newsViewModel.newsList[index]
//
//            Log.d("NewsCardRow", news.toString())
//
//            NewsCard(
//                news = news,
//                navController = navController
//            )
//        }
//        item {
//            if (newsViewModel.loading.value) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(10.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    CircularProgressIndicator(modifier = Modifier.height(50.dp))
//                }
//            }
//        }
//    }

    LazyRow(state = listState, modifier = Modifier.padding(start = 12.dp)) {
        items(newsList.size){ index ->
            val news = newsList[index]
            Log.d("NewsCardRow", news.toString())
            NewsCard(
                news = news,
//                navController = navController
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

