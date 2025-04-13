
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportapp.CleanArchitexture.domain.models.comments.CommentEntity
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.models.viewModels.CommentsViewModel
import com.example.sportapp.pagesAndWidgets.widgets.home.newsPageWidgets.commentItem.CommentItem
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Comments(currentNews: NewsEntity) {

    val commentsViewModel = CommentsViewModel()

    val page = remember { mutableStateOf(1) }
    val loading = remember { mutableStateOf(false) }
    val isLoaded = remember { mutableStateOf(false) }
    val itemList = remember { mutableStateListOf<CommentEntity>() }
    val listState = rememberLazyListState()
    val totalItems = remember { mutableStateOf(Int.MAX_VALUE) }


    LaunchedEffect(key1 = page.value) {

        if (itemList.size >= totalItems.value) return@LaunchedEffect

        loading.value = true
        itemList.addAll(
            commentsViewModel.loadComments(
                newsDateTime = currentNews.dateTime,
                pageNumber = page.value
            )
        )

        totalItems.value = commentsViewModel.commentsCount
        isLoaded.value = true
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Комментариев пока нет", style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                )
            )
        }
    } else {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            items(itemList.size) { index ->

                val comment = itemList[index]

                CommentItem(comment)
            }
            item {
                if (loading.value && itemList.size < totalItems.value) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.height(50.dp))
                    }
                }
            }
        }
    }
}




