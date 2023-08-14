package com.seda.notebookapp_compose.domain.usecase

import com.seda.notebookapp_compose.domain.model.Note
import com.seda.notebookapp_compose.domain.repository.NoteRepository

class GetNote(val repository: NoteRepository) {

    suspend operator fun invoke(id: Int):Note?{
     return   repository.getNoteById(id)
    }
}