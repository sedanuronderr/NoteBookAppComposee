package com.seda.notebookapp_compose.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seda.notebookapp_compose.domain.model.Note
import com.seda.notebookapp_compose.domain.usecase.NotUseCases
import com.seda.notebookapp_compose.domain.util.NoteOrder
import com.seda.notebookapp_compose.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel
    @Inject constructor(private val noteUseCases: NotUseCases)
    : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state
    private var recentlyDeletedNote: Note? = null
    private var getNotesJob: Job? = null

    init{
        getNotess(noteOrder = NoteOrder.Color(OrderType.Descending))
    }
    fun onEvent(event:NotesEvent){

        when(event){
            is NotesEvent.Order->{
                if(state.value.noteOrder::class == event.noteOrder::class && state.value.noteOrder.orderType == event.noteOrder.orderType){
                    return
                }

                getNotess(event.noteOrder)
            }

            is NotesEvent.DeleteNote->{
                  viewModelScope.launch {
                      noteUseCases.deleteNote(event.note)
                      recentlyDeletedNote = event.note
                  }

            }

            is NotesEvent.RestoreNote->{
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesEvent.ToggleOrderSection->{
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }

        }

    private fun getNotess(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)

    }


}