package com.example.sportapp.support2026.presentation.screeens.homeScreen.newsDetailsScreen.widgets.overlay

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CommentsOverlay(
//    showBar: MutableState<Boolean>,
    overlayVisible: MutableState<Boolean>,
//    authModel: AuthViewModel,
//    currentNews: NewsEntity,
//    CommentsCount: MutableState<Int>
) {

//    val commentsViewModel = CommentsViewModel()
//    val page = remember { mutableStateOf(1) }
//
//
//    val itemList = remember { mutableStateListOf<CommentEntity>() }
//
//    if (overlayVisible.value) {
//
//        showBar.value = false
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 4.dp)
//                .background(
//                    Color.White,
//                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
//                )
//        ) {
//            Column(
//                verticalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier.fillMaxSize()
//                    .padding(horizontal = 16.dp)
//            ) {
//                Text(
//                    text = "комментарии",
//                    style = TextStyle(textAlign = TextAlign.Center),
//                    modifier = Modifier.fillMaxWidth()
//                        .padding(vertical = 12.dp)
//                )
//
//
//                Comments(
//                    currentNews = currentNews,
//                    commentsViewModel,
//                    page,
//                    itemList,
//                    authModel.currentUser.value!!,
//                    Modifier.weight(1F)
//                )
//
//                SendCommentRow(authModel, commentsViewModel ,currentNews, itemList, CommentsCount)
//            }
//
//        }
//    }
}
