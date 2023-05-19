package com.plcoding.cleanarchitecturenoteapp.feature_note.data.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domaine.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase:RoomDatabase() {
    abstract val noteDao: NoteDAO

    companion object {
        const val DATABASE_NAME = "notesDb"
    }
}