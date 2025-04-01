package com.example.sportapp.widgets.aiChat

import ChatRepository
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportapp.models.viewModels.AIAnswerViewModel
import com.example.sportapp.widgets.aiChat.rowToSendPrompt.PromptTextField
import com.example.sportapp.widgets.aiChat.rowToSendPrompt.SendButton

@Composable
fun RowToSendPrompt(repository: ChatRepository, AIViewModel: AIAnswerViewModel, user: String) {
    var prompt by remember { mutableStateOf("") }

    Box(Modifier.padding(top = 4.dp, bottom = 8.dp)) {
        Box(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 8.dp)
                .background(color = Color.Black)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                PromptTextField(
                    prompt = prompt,
                    onPromptChange = { prompt = it },
                    Modifier.weight(1f)
                )

                Spacer(Modifier.width(12.dp))

                SendButton(
                    repository, AIViewModel, prompt, user, onPromptClear = { prompt = "" },
                )
            }
        }
    }
}
