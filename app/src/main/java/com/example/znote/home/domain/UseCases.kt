package com.example.znote.home.domain

import com.example.znote.home.data.NoteEntity
import com.example.znote.home.data.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetNotesUseCase(private val repository: NoteRepository) {

    suspend operator fun invoke(): List<Note> {
        return withContext(Dispatchers.IO) {
            repository.getAllNotes().map { noteEntity ->
                Note(
                    id = noteEntity.id,
                    content = noteEntity.content,
                    date = noteEntity.date,
                    color = noteEntity.color,
                )
            }
        }
    }
}

class AddNoteUseCase(private val repository: NoteRepository) {

    suspend operator fun invoke(note: Note) {
        withContext(Dispatchers.IO) {
            repository.insertNote(
                NoteEntity(
                    id = note.id,
                    content = note.content,
                    date = note.date,
                    color = note.color,

                    )
            )
        }
    }
}

class DeleteNoteUseCase(private val repository: NoteRepository) {

    suspend operator fun invoke(id: String) {
        withContext(Dispatchers.IO) {
            repository.deleteNoteById(id)
        }
    }
}

class UpdateNoteUseCase(private val repository: NoteRepository) {

    suspend operator fun invoke(note: Note) {
        withContext(Dispatchers.IO) {
            repository.updateNote(
                note = NoteEntity(
                    id = note.id,
                    content = note.content,
                    date = note.date,
                    color = note.color,
                )
            )
        }
    }
}

class SearchNoteUseCase(private val repository: NoteRepository) {

    suspend operator fun invoke(content: String): List<Note> {
        return withContext(Dispatchers.IO) {
            repository.searchNoteByContent(content).map { noteEntity ->
                Note(
                    id = noteEntity.id,
                    content = noteEntity.content,
                    date = noteEntity.date,
                    color = noteEntity.color,
                )
            }
        }
    }
}