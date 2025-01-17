package com.example.sportapp.pages

import AppActivityViewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NewsPage(
    appActivity: AppActivityViewModel,
    newsDateTime: String
) {
    appActivity.changePageName("")
    Text(text = newsDateTime)
}