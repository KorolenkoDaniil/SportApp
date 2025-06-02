package com.example.sportapp.presentation.widgets.screens.home.home.searchUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.ui.theme.red_accent_color
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

//@Composable
//fun SearchedNewsList(
//    newsViewModel: NewsActivityViewModel,
//    searchPrompt: String,
//    navController: NavController,
//    itemList: SnapshotStateList<NewsEntity>,
//    loading: MutableState<Boolean>
//) {
//    val page = remember { mutableStateOf(0) }
//    val listState = rememberLazyListState()
//    val lastSearchPrompt = remember { mutableStateOf("") }
//
//    // Обнуляем страницу и очищаем список при новом поисковом запросе
//    LaunchedEffect(searchPrompt) {
//        if (searchPrompt.isBlank()) return@LaunchedEffect
//
//        if (searchPrompt != lastSearchPrompt.value) {
//            lastSearchPrompt.value = searchPrompt
//            page.value = 0
//            itemList.clear()
//        }
//    }
//
//    // Загружаем новости при изменении страницы
//    LaunchedEffect(page.value) {
//        if (searchPrompt.isBlank()) return@LaunchedEffect
//
//        loading.value = true
//        newsViewModel.searchAndSetNews(
//            pageNumber = page.value,
//            searchPrompt = searchPrompt,
//            sportIndex = newsViewModel.sportIndex,
//            itemList = itemList,
//            clearElements = (page.value == 0)
//        )
//        loading.value = false
//    }
//
//    // Обработка скролла для подгрузки следующей страницы
//    LaunchedEffect(listState) {
//        snapshotFlow {
//            val visibleItemCount = listState.layoutInfo.visibleItemsInfo.size
//            val totalItemCount = listState.layoutInfo.totalItemsCount
//            val lastVisibleItemIndex = listState.firstVisibleItemIndex + visibleItemCount
//            Pair(lastVisibleItemIndex, totalItemCount)
//        }.collectLatest { (lastVisible, total) ->
//            if (!loading.value && lastVisible >= total - 2) {
//                page.value++
//            }
//        }
//    }
//
//    LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
//        items(itemList.size) { index ->
//            val news = itemList[index]
//            val thumbnail = rememberAsyncImagePainter(news.newsImage)
//
//            Image(
//                painter = thumbnail,
//                contentDescription = "",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//                    .clickable {
//                        val newsDateTime = news.dateTime
//                        navController.navigate("news/$newsDateTime")
//                    },
//                contentScale = ContentScale.Crop
//            )
//
//            Spacer(Modifier.height(8.dp))
//            Text(
//                text = news.title,
//                style = TextStyle(color = MaterialTheme.colorScheme.onBackground)
//            )
//            Spacer(Modifier.height(8.dp))
//            Text(text = news.sport, style = TextStyle(color = red_accent_color))
//            Spacer(Modifier.height(16.dp))
//        }
//
//        item {
//            if (loading.value) {
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
//}



@Composable
fun SearchedNewsList(
    newsViewModel: NewsActivityViewModel,
    searchPrompt: String,
    navController: NavController,
    itemList: SnapshotStateList<NewsEntity>,
    loading: MutableState<Boolean>
) {
    val page = remember { mutableStateOf(0) }
    val listState = rememberLazyListState()
    val lastSearchPrompt = remember { mutableStateOf("") }

    // Сброс при новом поиске
    LaunchedEffect(searchPrompt) {
        if (searchPrompt.isBlank()) return@LaunchedEffect

        delay(500) // debounce
        if (searchPrompt != lastSearchPrompt.value) {
            lastSearchPrompt.value = searchPrompt
            page.value = 0
            itemList.clear()
        }
    }

    // Загрузка данных при смене страницы
    LaunchedEffect(page.value, lastSearchPrompt.value) {
        if (searchPrompt.isBlank()) return@LaunchedEffect

        loading.value = true
        newsViewModel.searchAndSetNews(
            pageNumber = page.value,
            searchPrompt = searchPrompt,
            sportIndex = newsViewModel.sportIndex,
            itemList = itemList,
            clearElements = (page.value == 0)
        )
        loading.value = false
    }

    // Пагинация по скроллу
    LaunchedEffect(listState) {
        snapshotFlow {
            val visibleItemCount = listState.layoutInfo.visibleItemsInfo.size
            val totalItemCount = listState.layoutInfo.totalItemsCount
            val lastVisibleItemIndex = listState.firstVisibleItemIndex + visibleItemCount
            Pair(lastVisibleItemIndex, totalItemCount)
        }.collectLatest { (lastVisible, total) ->
            if (!loading.value && lastVisible >= total - 2) {
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
                        navController.navigate("news/$newsDateTime")
                    },
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(8.dp))
            Text(
                text = news.title,
                style = TextStyle(color = MaterialTheme.colorScheme.onBackground)
            )
            Spacer(Modifier.height(8.dp))
            Text(text = news.sport, style = TextStyle(color = red_accent_color))
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
