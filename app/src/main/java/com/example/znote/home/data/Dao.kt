package com.example.znote.home.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    // Query to get all notes ordered by id
    @Query("SELECT * FROM note_table ORDER BY date ASC")
    suspend fun getAllNotes(): List<NoteEntity>

    // Search for notes by content, using the LIKE operator
    @Query("SELECT * FROM note_table WHERE content LIKE '%' || :searchQuery || '%' ORDER BY date ASC")
    suspend fun searchNotesByContent(searchQuery: String): List<NoteEntity>

    // Insert a note into the table, replaces on conflict
    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    // Delete a specific note by its id
    @Query("DELETE FROM note_table WHERE id = :id")
    suspend fun deleteNoteById(id: String)

    // Optional: Delete the note using the object itself
    @Delete
    suspend fun delete(note: NoteEntity)

    // Optional: Update an existing note
    @Update
    suspend fun update(note: NoteEntity)
}
