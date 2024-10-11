package com.example.znote.home.data

import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {

    override suspend fun getAllNotes(): List<NoteEntity> {
        return noteDao.getAllNotes()
    }

    override suspend fun searchNoteByContent(content: String): List<NoteEntity> {
        return noteDao.searchNotesByContent(content)
    }

    override suspend fun insertNote(note: NoteEntity) {
        noteDao.insert(note)
    }

    override suspend fun deleteNoteById(id: String) {
        noteDao.deleteNoteById(id)
    }

    override suspend fun updateNote(note: NoteEntity) {
        noteDao.update(note = note)
    }
}
