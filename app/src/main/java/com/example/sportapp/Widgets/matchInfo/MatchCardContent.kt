package com.example.sportapp.widgets.matchInfo

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.sportapp.domain.EventEntity


@Composable
fun MatchCardContent(event: EventEntity) {
    Column {
        Text(event.type)
        Text(event.order.toString())
        Text(event.id)
        Text(event.type)
        Text(event.type)
        Text(event.type)
    }
}