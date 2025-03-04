package com.example.sportapp.models.chatMessage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "message_id")
    val id: Int = 0,

    @ColumnInfo(name = "message_text")
    val text: String,

    @ColumnInfo(name = "message_time")
    val time: Date,

    @ColumnInfo(name = "message_sender")
    val sender: String
) {
    constructor(text: String, sender: String) : this(
        id = 0,
        text = text,
        sender = sender,
        time = Date()
    )
}