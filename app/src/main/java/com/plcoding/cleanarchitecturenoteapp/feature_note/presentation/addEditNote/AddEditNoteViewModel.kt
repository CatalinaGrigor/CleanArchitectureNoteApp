package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.addEditNote

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.model.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.useCase.NotesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor (
    private val notesUseCases: NotesUseCases
    savedStateHandle: SavedStateHandle
        ): ViewModel() {

        private val _noteTitle= mutableStateOf(NoteTextFieldState(hint = "Enter title .."))
        val noteTitle: State<NoteTextFieldState> = _noteTitle

        private val _noteContent = mutableStateOf(NoteTextFieldState(hint = "Enter content .."))
        val noteContent: State<NoteTextFieldState> = _noteContent

        private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
        val noteColor: State<Int> = _noteColor

        private val _eventFlow = MutableSharedFlow<UiEvent>()
        val eventFlow = _eventFlow.asSharedFlow()

        private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId!=-1){
        }
    }

        fun onEvent (event: AddEditNoteEvent){
            when(event){
                is AddEditNoteEvent.EnteredTitle ->{
                    _noteTitle.value = noteTitle.value.copy(
                        text = event.value
                    )
                }
                is AddEditNoteEvent.ChangeTitleFocus ->{
                    _noteTitle.value = noteTitle.value.copy(
                        isHintVisible = !event.focusState.isFocused &&
                                noteTitle.value.text.isBlank()
                    )
                }
                is AddEditNoteEvent.EnteredContent ->{
                    _noteContent.value = noteContent.value.copy(
                        text = event.value
                    )
                }
                is AddEditNoteEvent.ChangedContentFocus ->{
                    _noteContent.value = noteContent.value.copy(
                        isHintVisible = !event.focusState.isFocused &&
                                noteContent.value.text.isBlank()
                    )
                }
                is AddEditNoteEvent.ChangeColor ->{
                    _noteColor.value = event.color
                }
                is AddEditNoteEvent.SaveNote ->{
                    viewModelScope.launch {
                        try{
                            notesUseCases.addNote(
                                Note(
                                    title = noteTitle.value.text,
                                    content = noteContent.value.text,
                                    timestamp = System.currentTimeMillis(),
                                    color = noteColor.value,
                                     id = currentNoteId
                                )
                            )
                            _eventFlow.emit(UiEvent.SaveNote)
                        }catch (e:InvalidNoteException){
                            _eventFlow.emit(UiEvent.ShowSnackbar(message = e.message ?:"Couldn't save note"))
                        }
                    }
                }
            }
        }

        sealed class UiEvent {
            data class ShowSnackbar (val message: String): UiEvent()
            object SaveNote:UiEvent()
        }
}