package com.seda.notebookapp_compose.domain.usecase

data class NotUseCases(
    val getNote: GetNote,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNotes: GetNotes
) {

}
