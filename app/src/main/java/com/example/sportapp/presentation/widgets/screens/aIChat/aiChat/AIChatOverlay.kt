package com.example.sportapp.presentation.widgets.screens.aIChat.aiChat

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sportapp.containers.ViewModelContainer
import com.example.sportapp.domain.viewModels.authorization.AuthViewModel
import com.example.sportapp.presentation.widgets.screens.aIChat.aiChat.messagesColumn.AIChatMessages
import com.example.sportapp.presentation.widgets.screens.aIChat.aiChat.rowToSendPrompt.SendPromptRow

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AIChatOverlay(
    authModel: AuthViewModel,
    horizontalPaddings: Dp,
    viewModels: ViewModelContainer,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp)
            .background(
                MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .weight(1F)
        ) {

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Чат с нейростью",
                style = TextStyle(textAlign = TextAlign.Center),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )


            AIChatMessages(
                AIViewModel = viewModels.aiViewModel,
                authViewModel = authModel,
                horizontalPaddings = horizontalPaddings,
                modifier = Modifier.weight(1f)
            )

            SendPromptRow(
                authModel = authModel,
                messageViewModel = viewModels.aiViewModel
            )
        }
    }
}
