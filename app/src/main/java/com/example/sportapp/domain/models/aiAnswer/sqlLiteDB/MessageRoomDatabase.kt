package com.example.sportapp.CleanArchitexture.domain.models.aiAnswer.sqlLiteDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sportapp.CleanArchitexture.domain.models.aiAnswer.MessageEntity

@Database(entities = [MessageEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class MessageRoomDatabase : RoomDatabase() {

    abstract fun messageDao(): MessagesDAO

    companion object {
        @Volatile
        private var INSTANCE: MessageRoomDatabase? = null

        fun getInstance(context: Context): MessageRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MessageRoomDatabase::class.java,
                    "Messages"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}