package com.seda.notebookapp_compose

sealed class Screen(val route: String) {
    object NoteScreen: Screen("notes_screen")
    object AddEditNoteScreen: Screen("add_edit_note_screen")
}