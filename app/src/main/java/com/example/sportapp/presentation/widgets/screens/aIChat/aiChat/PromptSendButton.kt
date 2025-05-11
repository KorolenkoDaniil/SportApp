package com.example.sportapp.presentation.widgets.screens.aIChat.aiChat

import android.annotation.SuppressLint
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
import com.example.sportapp.CleanArchitexture.domain.models.aiAnswer.MessageEntity
import com.example.sportapp.R
import com.example.sportapp.domain.viewModels.authorization.AuthViewModel
import com.example.sportapp.models.viewModels.AIAnswerViewModel
import com.example.sportapp.ui.theme.red_accent_color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("NewApi")
@Composable
fun PromptSendButton(messageViewModel: AIAnswerViewModel, authViewModel: AuthViewModel, message: String, onPromptClear: () -> Unit){
    Button(
        onClick = {
            CoroutineScope(Dispatchers.IO).launch {

                messageViewModel.messagesList.addFirst(MessageEntity(
                    userEmail = authViewModel.currentUser.value!!.email,
                    messageText = message,
                    isAiAnswer = false
                ))

                messageViewModel.loadAIAnswer(
                    prompt = message,
                    user = authViewModel.currentUser.value!!.email
                )
            }

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