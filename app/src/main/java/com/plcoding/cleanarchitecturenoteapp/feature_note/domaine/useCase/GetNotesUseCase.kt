package com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.useCase

import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.repository.NoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.util.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase (private val repository: NoteRepository) {
    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)): Flow<List<Note>>{
        return repository.getNotes().map { notes ->
            when (noteOrder.orderType){
                is OrderType.Ascending -> {
                    when(noteOrder){
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                        is NoteOrder.Color -> notes.sortedBy { it.color}
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder){
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}