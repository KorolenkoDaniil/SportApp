package com.example.sportapp.pages.widgets.aiChat.messagesColumn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.sportapp.models.chatMessage.MessageEntity

@Composable
fun Message (message: MessageEntity, color: Color, shape: RoundedCornerShape, paddingSide: Boolean, textStyle: TextStyle) {
    Box (if (paddingSide){Modifier.padding(end = 16.dp)} else Modifier.padding(start = 24.dp)) {
        Box(
            Modifier.wrapContentHeight()
                .fillMaxWidth()
                .background(color = color, shape)
                .padding(8.dp)
        ) {
            Text(text = message.text, style = textStyle)
        }
    }
}
