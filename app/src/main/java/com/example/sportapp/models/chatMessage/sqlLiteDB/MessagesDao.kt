package com.example.sportapp.models.chatMessage.sqlLiteDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportapp.models.chatMessage.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessagesDAO {
    @Query("SELECT * FROM messages where user = :user ORDER BY message_id ASC")
    fun getAllMessages(user: String): Flow<List<MessageEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)


    @Query("""
    DELETE FROM messages
    WHERE user = :user AND ROWID NOT IN (
        SELECT ROWID FROM messages
        WHERE user = :user
        ORDER BY ROWID DESC
        LIMIT :limit
    )
""")
    fun deleteExtraMessages(user: String, limit: Int)
}