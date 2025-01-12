package com.example.sportapp.models.youtube.domain


data class VideoEntity(
    val kind: String,
    val etag: String,
    val id: VideoIdEntity,
    val snippet: SnippetEntity
)