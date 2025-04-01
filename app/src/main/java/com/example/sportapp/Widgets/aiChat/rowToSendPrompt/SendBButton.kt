package com.example.sportapp.widgets.aiChat.rowToSendPrompt

import ChatRepository
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sportapp.R
import com.example.sportapp.models.chatMessage.MessageEntity
import com.example.sportapp.models.viewModels.AIAnswerViewModel
import com.example.sportapp.ui.theme.red_accent_color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SendButton(repository: ChatRepository, AIViewModel: AIAnswerViewModel, prompt: String, user: String, onPromptClear: () -> Unit){
    Button(
        onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                repository.addMessage(MessageEntity(text = prompt, sender = "User", user), user)
            }

            AIViewModel.loadAIAnswer(prompt, repository, user = user)

            onPromptClear()

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