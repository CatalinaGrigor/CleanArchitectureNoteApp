package com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.useCase

data class NotesUseCases(
    val getNotes: GetNotesUseCase,
    val deleteNote: DeleteNoteUseCase,
    val getNoteById: GetNoteByIdUseCase,
    val addNote: AddNoteUseCase
)
