package com.example.sportapp.models.chatMessage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "messageId")
    val id: Int = 0,

    @ColumnInfo(name = "messageText")
    val text: String,

    @ColumnInfo(name = "messageTime")
    val time: LocalDateTime,

    @ColumnInfo(name = "messageSender")
    val sender: String
) {
    constructor(text: String, sender: String) : this(
        id = 0,  // Room сам установит правильный автоинкрементный ID
        text = text,
        sender = sender,
        time = LocalDateTime.now()
    )
}
