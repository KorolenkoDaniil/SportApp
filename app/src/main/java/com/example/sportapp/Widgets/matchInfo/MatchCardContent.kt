package com.example.sportapp.widgets.matchInfo

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.sportapp.domain.EventResponseEntity

@Composable
fun MatchCardContent(event: EventResponseEntity) {
    Column {
        Text(event.type)
        Text(event.order.toString())
        Text(event.id)
        Text(event.type)
        Text(event.type)
        Text(event.type)
    }
}