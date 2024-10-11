package com.example.znote.home.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "note_table")
data class NoteEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val content: String = "",
    val date: String = "",
    val color:Int = Color(0xffd7e6f9).toArgb()
)
