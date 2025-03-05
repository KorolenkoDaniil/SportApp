package com.example.sportapp.widgets.aiChat.rowToSendPrompt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PromptTextField(
    prompt: String,
    onPromptChange: (String) -> Unit,
    modifier: Modifier
) {
    BasicTextField(
        value = prompt,
        onValueChange = onPromptChange,
        modifier = modifier
            .background(Color.Transparent, RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxWidth()
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
}
