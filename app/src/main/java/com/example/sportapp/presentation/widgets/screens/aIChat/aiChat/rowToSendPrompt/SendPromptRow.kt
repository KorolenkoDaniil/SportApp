//package com.example.sportapp.presentation.widgets.screens.aIChat.aiChat.rowToSendPrompt
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.focus.FocusRequester
//import androidx.compose.ui.focus.focusRequester
//import androidx.compose.ui.focus.onFocusChanged
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.sportapp.domain.viewModels.authorization.AuthViewModel
//import com.example.sportapp.models.viewModels.AIAnswerViewModel
//
//
////TODO сделать общую строку отправки
//
//@SuppressLint("StateFlowValueCalledInComposition")
//@Composable
//fun SendPromptRow(
//    authModel: AuthViewModel,
//    messageViewModel: AIAnswerViewModel,
//) {
//
//    var prompt by remember { mutableStateOf("") }
//    var isFocused by remember { mutableStateOf(false) }
//    val focusRequester = remember { FocusRequester() }
//
//    Box(
//        modifier = Modifier
//            .padding(bottom = 0.dp)
//            .background(color = Color(0xFFF5F5F5))
//            .fillMaxWidth()
//            .height(60.dp)
//    ) {
//        Column(verticalArrangement = Arrangement.Center) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .fillMaxSize()
//            ) {
//                BasicTextField(
//                    value = prompt,
//                    onValueChange = { prompt = it },
//                    modifier = Modifier
//                        .background(Color.Transparent, RoundedCornerShape(16.dp))
//                        .padding(horizontal = 12.dp, vertical = 8.dp)
//                        .fillMaxWidth()
//                        .weight(1F)
//                        .focusRequester(focusRequester)
//                        .onFocusChanged { focusState ->
//                            isFocused = focusState.isFocused
//                        },
//                    singleLine = true,
//                    textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
//                    decorationBox = { innerTextField ->
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            if (prompt.isEmpty() && !isFocused) {
//                                Text(
//                                    text = "спроси, нейронка ответит",
//                                    color = Color.Gray,
//                                    fontSize = 14.sp,
//                                    fontWeight = FontWeight.Normal
//                                )
//                            }
//                            innerTextField()
//                        }
//                    }
//                )
//
//                PromptSendButton(
//                    messageViewModel = messageViewModel,
//                    authViewModel = authModel,
//                    message = prompt,
//                    onPromptClear = {
//                        prompt = ""
//                        isFocused = false
//                        focusRequester.freeFocus()
//                    },
//                )
//            }
//        }
//    }
//}
