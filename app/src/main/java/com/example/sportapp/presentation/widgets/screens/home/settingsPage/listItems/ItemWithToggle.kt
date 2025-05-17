package com.example.sportapp.presentation.widgets.screens.home.settingsPage.listItems

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun ItemWithToggle(drawableResource: Painter, text: String){

    val value = remember { mutableStateOf(false) }

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

            Text(text = text)
        }

        Spacer(modifier = Modifier.weight(1f))

        Switch(
            checked = value.value,
            onCheckedChange = {
                value.value = !value.value
            },
            modifier = Modifier.height(20.dp)
        )
    }
}