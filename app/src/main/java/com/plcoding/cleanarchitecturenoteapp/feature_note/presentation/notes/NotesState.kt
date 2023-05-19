package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.util.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
