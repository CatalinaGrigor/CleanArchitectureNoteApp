package com.plcoding.cleanarchitecturenoteapp.di

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.Room
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.dataSource.NoteDatabase
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.NoteRepositoryImpl
import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.repository.NoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.useCase.AddNoteUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.useCase.DeleteNoteUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.useCase.GetNoteByIdUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.useCase.GetNotesUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.useCase.NotesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application):NoteDatabase{
        return Room.databaseBuilder(
            /* context = */ app,
            /* klass = */ NoteDatabase::class.java,
            /* name = */ NoteDatabase.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun providesNoteRepository (db: NoteDatabase): NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }
    @Provides
    @Singleton
    fun providesNoteRepository (repository: NoteRepository): NotesUseCases{
        return NotesUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            getNoteById = GetNoteByIdUseCase(repository),
            addNote = AddNoteUseCase(repository)
        )
    }
}