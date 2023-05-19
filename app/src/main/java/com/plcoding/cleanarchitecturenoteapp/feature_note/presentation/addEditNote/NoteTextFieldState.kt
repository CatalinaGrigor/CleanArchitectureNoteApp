package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.addEditNote

data class NoteTextFieldState(
    val text:String ="",
    val hint:String = "",
    val isHintVisible: Boolean = true
)
