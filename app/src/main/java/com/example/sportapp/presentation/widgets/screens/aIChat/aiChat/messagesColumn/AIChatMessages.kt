//package com.example.sportapp.presentation.widgets.screens.aIChat.aiChat.messagesColumn
//
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.snapshotFlow
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import com.example.sportapp.domain.viewModels.authorization.AuthViewModel
//import com.example.sportapp.models.viewModels.AIAnswerViewModel
//import kotlinx.coroutines.flow.collectLatest
//
//@Composable
//fun AIChatMessages(AIViewModel: AIAnswerViewModel, authViewModel: AuthViewModel, horizontalPaddings: Dp, modifier: Modifier = Modifier) {
//
//    val listState = rememberLazyListState()
//
//    LaunchedEffect (Unit) {
//        if (AIViewModel.messagesList.isEmpty()) {
//            AIViewModel.loading.value = true
//            AIViewModel.loadAIChatHistory(
//                email = authViewModel.currentUser.value!!.email
//            )
//            AIViewModel.loading.value = false
//        }
//    }
//
//    LaunchedEffect (listState) {
//        if (AIViewModel.page.value > 0) {
//            snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
//                .collectLatest { index ->
//                    if (!AIViewModel.loading.value && index != null && index >= AIViewModel.messagesList.size - 5) {
//                        AIViewModel.page.value++
//
//                        AIViewModel.loading.value = true
//                        AIViewModel.loadAIChatHistory(
//                            email = authViewModel.currentUser.value!!.email
//                        )
//                        AIViewModel.loading.value = false
//                    }
//                }
//        }
//    }
//
//    LaunchedEffect(AIViewModel.shouldScrollToBottom.value) {
//        if (AIViewModel.shouldScrollToBottom.value) {
//            if (AIViewModel.messagesList.isNotEmpty()) {
//                listState.animateScrollToItem(AIViewModel.messagesList.lastIndex)
//            }
//            AIViewModel.shouldScrollToBottom.value = false
//        }
//    }
//
//
//    LazyColumn(
//        state = listState,
//        modifier = modifier.padding(start = horizontalPaddings)
//    ){
//        items(AIViewModel.messagesList.size){ index ->
//            MessageItem(
//                item = AIViewModel.messagesList[index]
//            )
//        }
//        item {
//            if (AIViewModel.loading.value) {
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
//
//
