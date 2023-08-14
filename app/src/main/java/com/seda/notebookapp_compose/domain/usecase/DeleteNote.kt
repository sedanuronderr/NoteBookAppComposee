package com.seda.notebookapp_compose.domain.usecase

import com.seda.notebookapp_compose.domain.model.Note
import com.seda.notebookapp_compose.domain.repository.NoteRepository

class DeleteNote(val repository: NoteRepository) {

    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}