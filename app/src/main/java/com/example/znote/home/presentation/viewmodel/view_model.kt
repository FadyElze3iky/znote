package com.example.znote.home.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.znote.home.domain.AddNoteUseCase
import com.example.znote.home.domain.DeleteNoteUseCase
import com.example.znote.home.domain.GetNotesUseCase
import com.example.znote.home.domain.Note
import com.example.znote.home.domain.SearchNoteUseCase
import com.example.znote.home.domain.UpdateNoteUseCase
import com.example.znote.ui.theme.ColorList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val seachNoteUseCase: SearchNoteUseCase,
) : ViewModel() {

    // State to hold the list of notes
    var notes by mutableStateOf<List<Note>>(emptyList())
        private set

    // Function to load all notes from the repository
    fun loadNotes() {
        viewModelScope.launch {
            notes = getNotesUseCase().reversed()  // Fetch all notes using the use case

        }
    }

    fun addNote(content: String) {
        viewModelScope.launch {

            val note = Note(
                id = UUID.randomUUID().toString(),  // Generate a unique ID
                content = content,
                date = System.currentTimeMillis().toString()  // Use the formatted date string,
                , color = ColorList[Random.nextInt(ColorList.size)].toArgb()
            )

            addNoteUseCase(note)  // Call use case to insert note
            loadNotes()  // Reload notes after adding
        }
    }

    // Function to delete a note by its ID
    fun deleteNoteById(id: String) {
        viewModelScope.launch {
            deleteNoteUseCase(id)  // Call use case to delete note
            loadNotes()  // Reload notes after deleting
        }
    }

    fun updateNote(note: Note) {

        viewModelScope.launch {
            val updatedNote = Note(
                id = note.id,
                content = note.content,
                date = System.currentTimeMillis().toString(),
            )
            updateNoteUseCase(updatedNote)
            loadNotes()
        }
    }

    fun searchNote(content: String) {
        viewModelScope.launch {
            notes = seachNoteUseCase(content).reversed()
        }
    }

}
