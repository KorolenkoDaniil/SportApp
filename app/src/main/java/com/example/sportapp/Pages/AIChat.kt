package com.example.sportapp.pages

import ChatRepository
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.sportapp.models.chatMessage.sqlLiteDB.MessageRoomDatabase
import com.example.sportapp.models.viewModels.AIAnswerViewModel
import com.example.sportapp.widgets.aiChat.MessagesColumn
import com.example.sportapp.widgets.aiChat.RowToSendPrompt

@Composable
fun AIChatPage(
    AIViewModel: AIAnswerViewModel,
) {


    val listState = rememberLazyListState()

    val context = LocalContext.current
    val messageDb = remember { MessageRoomDatabase.getInstance(context) }
    val messageDao = remember { messageDb.messageDao() }
    val repository = remember { ChatRepository(messageDao, context) }
    val messagesState by remember { repository.messagesList }.collectAsState(initial = emptyList())

    LaunchedEffect(messagesState.size) {
        if (messagesState.isNotEmpty()) {
            listState.animateScrollToItem(messagesState.lastIndex)
        }
    }

    Column(Modifier.fillMaxSize()) {

        MessagesColumn(listState, messagesState, modifier = Modifier.weight(1f))

        RowToSendPrompt(repository, AIViewModel)
    }
}

