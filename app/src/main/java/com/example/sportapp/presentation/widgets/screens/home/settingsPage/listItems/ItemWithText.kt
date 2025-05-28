package com.example.sportapp.presentation.widgets.screens.home.settingsPage.listItems

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.sportapp.ui.theme.SettingTextStyle

@Composable
fun ItemWithTextField(drawableResource: Painter, text: String, input: MutableState<String>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(0.6f)
        ) {
            Image(
                painter = drawableResource,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = text, style = MaterialTheme.typography.SettingTextStyle)
        }

        Spacer(Modifier.width(16.dp))

        TextField(
            value = input.value,
            onValueChange = { input.value = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Gray,
                focusedIndicatorColor = Color.Blue
            )
        )

    }
}
