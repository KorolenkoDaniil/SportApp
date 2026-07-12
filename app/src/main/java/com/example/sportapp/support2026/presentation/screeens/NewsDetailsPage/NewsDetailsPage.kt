package com.example.sportapp.support2026.presentation.screeens.NewsDetailsPage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NewsDetailsPage (
    newsId: Int
){

    Text(text = "OneNewsPage " + newsId.toString())
}