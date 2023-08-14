package com.seda.notebookapp_compose.presentation.notes

import com.seda.notebookapp_compose.domain.model.Note
import com.seda.notebookapp_compose.domain.util.NoteOrder

sealed class NotesEvent
{
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}