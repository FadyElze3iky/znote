package com.example.znote.home.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.znote.home.data.NoteDao
import com.example.znote.home.data.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao  // Abstract function to get the DAO
}
