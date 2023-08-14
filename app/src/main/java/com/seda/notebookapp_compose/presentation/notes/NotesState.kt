package com.seda.notebookapp_compose.presentation.notes

import com.seda.notebookapp_compose.domain.model.Note
import com.seda.notebookapp_compose.domain.util.NoteOrder
import com.seda.notebookapp_compose.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder : NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible:Boolean=false

    )
