package com.example.sportapp.pages

import AppActivityViewModel
import ChatRepository
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportapp.R
import com.example.sportapp.models.chatMessage.MessageEntity
import com.example.sportapp.models.chatMessage.sqlLiteDB.MessageRoomDatabase
import com.example.sportapp.models.viewModels.AIAnswerViewModel
import com.example.sportapp.models.viewModels.AnswerState
import com.example.sportapp.ui.theme.Blue100
import com.example.sportapp.ui.theme.companion_messages
import com.example.sportapp.ui.theme.my_messages
import com.example.sportapp.ui.theme.red_accent_color
import com.example.sportapp.widgets.messages.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LikePage(
    appActivity: AppActivityViewModel,
    AIViewModel: AIAnswerViewModel,
    answerState: AnswerState
) {
    var prompt by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    val context = LocalContext.current
    val messageDb = remember { MessageRoomDatabase.getInstance(context) }
    val messageDao = remember { messageDb.messageDao() }
    val repository = remember { ChatRepository(messageDao) }
    val messagesState by remember { repository.messagesList }.collectAsState(initial = emptyList())

    LaunchedEffect(messagesState.size) {
        if (messagesState.isNotEmpty()) {
            listState.animateScrollToItem(messagesState.lastIndex)
        }
    }

    Column(Modifier.fillMaxSize()) {
        Box(Modifier.weight(1f)) {
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

        Box(Modifier.padding(vertical = 24.dp)) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BasicTextField(
                        value = prompt,
                        onValueChange = { prompt = it },
                        modifier = Modifier
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                            .fillMaxWidth()
                            .weight(1f)
                            .heightIn(min = 24.dp, max = 150.dp),
                        singleLine = false,
                        textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
                        decorationBox = { innerTextField ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (prompt.isEmpty()) {
                                    Text(
                                        text = "Введите свой вопрос",
                                        color = Color.Gray,
                                        fontSize = 16.sp,
                                        textAlign = TextAlign.Start
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )

                    Spacer(Modifier.width(12.dp))

                    Button(
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                repository.addMessage(MessageEntity(text = prompt, sender = "User"))
                            }

                            AIViewModel.loadAIAnswer(prompt, repository)

                        },
                        modifier = Modifier.size(32.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = red_accent_color,
                            contentColor = Color.White
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.send_prompt),
                            contentDescription = "Отправить",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

