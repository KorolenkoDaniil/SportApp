package com.example.sportapp.widgets.BottomBar


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    val text = remember { mutableStateOf("") }

    BasicTextField(
        maxLines = 1,
        value = text.value,
        onValueChange = { text.value = it },
        textStyle = TextStyle(color = Color.Black),
        modifier = modifier
            .border(width = 0.dp, color = Color.Transparent, shape = RoundedCornerShape(30.dp))
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .height(40.dp),
        decorationBox = { innerTextField ->
            Row(
                Modifier
                    .background(Color.White, shape = RoundedCornerShape(30.dp))
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(Icons.Filled.Search, contentDescription = "Search Icon")
                Box(Modifier.weight(1f)) {
                    if (text.value.isEmpty()) {
                        Text("Search...", color = Color.Gray)
                    }
                    innerTextField()
                }
                if (text.value.isNotEmpty()) {
                    IconButton(
                        modifier = Modifier.size(30.dp),
                        onClick = { text.value = "" }) {
                        Icon(Icons.Filled.Clear, contentDescription = "Clear Text")
                    }
                }
            }
        }
    )
}

