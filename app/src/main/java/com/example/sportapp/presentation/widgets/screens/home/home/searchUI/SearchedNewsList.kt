package com.example.sportapp.presentation.widgets.screens.home.home.searchUI

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchedNEwsList(newsViewModel: NewsActivityViewModel, searchPrompt: String, navController: NavController, itemList: SnapshotStateList<NewsEntity>, page: MutableState<Int>, loading: MutableState<Boolean>, listState: LazyListState) {

    LaunchedEffect(key1 = page.value) {
        loading.value = true

        val result = newsViewModel.searchNewsSuspend(page.value, searchPrompt, newsViewModel.sportIndex)
        result?.let {
            itemList.addAll(it.news)
        }

        loading.value = false
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collectLatest { index ->
                if (!loading.value && index != null && index >= itemList.size - 5) {
                    page.value++
                }
            }
    }

    LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
        items(itemList.size) { index ->
            val news = itemList[index]
            val thumbnail = rememberAsyncImagePainter(news.newsImage)

            Image(
                painter = thumbnail,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable {
                        val newsDateTime = news.dateTime
                        Log.d("ttt", "pageeee  $newsDateTime" )

                        navController.navigate("news/$newsDateTime")
                        newsViewModel.selectedNews = news
                    },
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(4.dp))
            Text(text = news.title)
            Spacer(Modifier.height(16.dp))
            Text(text = news.sport)
            Spacer(Modifier.height(16.dp))
        }

        item {
            if (loading.value) {
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