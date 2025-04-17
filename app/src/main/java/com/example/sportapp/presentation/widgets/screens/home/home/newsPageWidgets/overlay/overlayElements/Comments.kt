package com.example.sportapp.presentation.widgets.screens.home.home.newsPageWidgets.overlay.overlayElements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportapp.CleanArchitexture.domain.models.comments.CommentEntity
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.models.viewModels.CommentsViewModel
import com.example.sportapp.presentation.widgets.screens.home.home.newsPageWidgets.overlay.comments.commentItem.CommentItem
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Comments(currentNews: NewsEntity, commentsViewModel: CommentsViewModel, page: MutableState<Int>, itemList: SnapshotStateList<CommentEntity>, modifier: Modifier) {

    val loading = remember { mutableStateOf(false) }
    val isLoaded = remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val totalItems = remember { mutableStateOf(Int.MAX_VALUE) }

    LaunchedEffect(page.value) {
        if (itemList.size < totalItems.value) {
            loading.value = true
            val comments = commentsViewModel.loadComments(
                newsDateTime = currentNews.dateTime,
                pageNumber = page.value
            )
            itemList.addAll(comments)
            totalItems.value = commentsViewModel.commentsCount
            loading.value = false
            isLoaded.value = true
        }
    }


    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collectLatest { index ->
                if (!loading.value && index != null && index >= itemList.size - 5) {
                    page.value++
                }
            }
    }

    if (itemList.isEmpty() && isLoaded.value) {
        Text(
            text = "Комментариев к этой новости пока нету\n поделитесь своим мнением",
            style = TextStyle(color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Center),
            modifier = Modifier.fillMaxWidth()
        )
    } else {
        LazyColumn(state = listState, modifier = modifier.fillMaxWidth()) {
            items(itemList.size) { index ->
                val comment = itemList[index]
                CommentItem(comment)
            }

            item {
                if (loading.value) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.height(50.dp))
                    }
                }
            }
        }
    }
}


