package com.example.sportapp.api.entities.youtube


data class VideoEntity(
    val kind: String,
    val etag: String,
    val id: VideoId,
    val snippet: SnippetEntity
)