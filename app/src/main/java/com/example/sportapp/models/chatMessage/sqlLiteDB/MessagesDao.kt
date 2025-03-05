package com.example.sportapp.models.chatMessage.sqlLiteDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportapp.models.chatMessage.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessagesDAO {
    @Query("SELECT * FROM messages ORDER BY message_id ASC")
    fun getAllMessages(): Flow<List<MessageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)

    @Query("DELETE FROM messages " +
            "WHERE ROWID NOT IN " +
            "( SELECT ROWID FROM messages " +
            "ORDER BY ROWID DESC LIMIT :limit);")
    fun deleteExtraMessages(limit: Int)
}