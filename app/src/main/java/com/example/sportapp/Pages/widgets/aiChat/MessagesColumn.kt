package com.example.sportapp.pages.widgets.aiChat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportapp.models.chatMessage.MessageEntity
import com.example.sportapp.pages.widgets.aiChat.messagesColumn.Message
import com.example.sportapp.ui.theme.Blue100
import com.example.sportapp.ui.theme.companion_messages
import com.example.sportapp.ui.theme.my_messages

@Composable
fun MessagesColumn(listState: LazyListState, messagesState: List<MessageEntity>, modifier: Modifier) {
    Box(modifier.padding(horizontal = 8.dp)) {
        LazyColumn(
            Modifier.fillMaxSize(),
            state = listState,
            reverseLayout = false
        ) {
            itemsIndexed(messagesState) { _, item ->
                if (item.sender == "User") {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Message(
                            item,
                            Color.White,
                            RoundedCornerShape(
                                topStart = 16.dp,
                                topEnd = 16.dp,
                                bottomStart = 16.dp,
                                bottomEnd = 0.dp,
                            ),
                            false,
                            my_messages
                        )
                    }
                } else {
                    Row(Modifier.fillMaxWidth()) {
                        Message(
                            item,
                            Blue100,
                            RoundedCornerShape(
                                topStart = 16.dp,
                                topEnd = 16.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 16.dp
                            ),
                            true,
                            companion_messages
                        )
                    }
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}