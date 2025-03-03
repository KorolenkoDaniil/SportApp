package com.example.sportapp.widgets.messages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportapp.models.chatMessage.MessageEntity

@Composable
fun Message (message: MessageEntity, color: Color, shape: RoundedCornerShape) {
    Box(
        Modifier.wrapContentHeight()
            .width(168.dp)
            .background(color = color, shape)
            .padding(8.dp)
    ){
        Text(text = message.text)
    }
}