package com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.useCase

import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.repository.NoteRepository

class DeleteNoteUseCase (private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}