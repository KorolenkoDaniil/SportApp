package com.example.sportapp.models.youtube.domain

import com.example.sportapp.models.youtube.api.entities.youtube.Thumbnails

data class SnippetEntity(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
    val channelTitle: String,
    val liveBroadcastContent: String,
    val publishTime: String
)
