package com.example.sportapp.models.youtube.domain

import com.example.sportapp.models.youtube.api.entities.youtube.VideoId


data class VideoEntity(
    val kind: String,
    val etag: String,
    val id: VideoId,
    val snippet: SnippetEntity
)