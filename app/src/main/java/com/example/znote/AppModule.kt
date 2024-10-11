package com.example.znote

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.znote.home.data.NoteDao
import com.example.znote.home.data.NoteDatabase
import com.example.znote.home.data.NoteRepository
import com.example.znote.home.data.NoteRepositoryImpl
import com.example.znote.home.domain.AddNoteUseCase
import com.example.znote.home.domain.DeleteNoteUseCase
import com.example.znote.home.domain.GetNotesUseCase
import com.example.znote.home.domain.SearchNoteUseCase
import com.example.znote.home.domain.UpdateNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent // Use SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext // Provide the application context
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(appContext: Context): NoteDatabase {
        return Room.databaseBuilder(
            appContext,
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.noteDao()  // Use the DAO from the database
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepositoryImpl(noteDao)  // Pass the DAO to the repository implementation
    }

    @Provides
    fun provideGetNotesUseCase(repository: NoteRepository): GetNotesUseCase {
        return GetNotesUseCase(repository)
    }

    @Provides
    fun provideAddNoteUseCase(repository: NoteRepository): AddNoteUseCase {
        return AddNoteUseCase(repository)
    }

    @Provides
    fun provideDeleteNoteUseCase(repository: NoteRepository): DeleteNoteUseCase {
        return DeleteNoteUseCase(repository)
    }

    @Provides
    fun provideUpdateNoteUseCase(repository: NoteRepository): UpdateNoteUseCase {
        return UpdateNoteUseCase(repository)
    }

    @Provides
    fun provideSearchNoteUseCase(repository: NoteRepository): SearchNoteUseCase {
        return SearchNoteUseCase(repository)
    }

}
