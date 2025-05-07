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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.presentation.widgets.common.shared.NewsCard
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewsCardRow(
    navController: NavHostController,
    newsViewModel: NewsActivityViewModel,
    horizontalPaddings: Dp,
    itemList: SnapshotStateList<NewsEntity>
) {

    //TODO сделать проверку и убрать лищние зщапросы

    val page = remember { mutableStateOf(1) }
    val loading = remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    LaunchedEffect (key1 = page.value) {
        loading.value = true
        newsViewModel.searchAndSetNews(
            pageNumber = page.value,
            searchPrompt = "",
            sportIndex = -1,
            itemList = itemList,
            clearElements = false
        )
        loading.value = false
    }

    LaunchedEffect (listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collectLatest {  index ->
                if (!loading.value && index != null && index >= itemList.size - 5){
                    page.value++
                }
            }
    }


    LazyRow(state = listState, modifier = Modifier.padding(start = horizontalPaddings)) {
        items(itemList.size){ index ->

            val newss = itemList[index]

            NewsCard(
                news = newss,
                navController = navController
            )
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

