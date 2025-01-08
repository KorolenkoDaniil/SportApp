package com.example.sportapp.pages

import AppActivityViewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LikePage(
    appActivity: AppActivityViewModel
) {
    appActivity.changePageName("")
    Text(text = "LikePage")
}