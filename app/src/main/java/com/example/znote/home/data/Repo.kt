package com.example.znote.home.data

interface NoteRepository {

    suspend fun getAllNotes(): List<NoteEntity>
    suspend fun searchNoteByContent(content: String): List<NoteEntity>
    suspend fun insertNote(note: NoteEntity)
    suspend fun deleteNoteById(id: String)
    suspend fun updateNote(note: NoteEntity)
}