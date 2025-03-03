package com.example.sportapp.models.chatMessage.sqlLiteDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportapp.models.chatMessage.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessagesDAO {
    @Query("SELECT * FROM Messages ORDER BY messageId ASC")
    fun getAllMessages(): Flow<List<MessageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)
}
