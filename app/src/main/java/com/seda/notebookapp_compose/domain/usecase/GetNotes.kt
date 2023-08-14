package com.seda.notebookapp_compose.domain.usecase

import android.provider.ContactsContract
import com.seda.notebookapp_compose.domain.model.Note
import com.seda.notebookapp_compose.domain.repository.NoteRepository
import com.seda.notebookapp_compose.domain.util.NoteOrder
import com.seda.notebookapp_compose.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class GetNotes( val repository: NoteRepository) {

 operator fun invoke(noteOrder: NoteOrder=NoteOrder.Date(OrderType.Descending)): Flow<List<Note>> {
      return  repository.getNotes().map { notes->
          when(noteOrder.orderType){//Ascending or descending
              is OrderType.Ascending->{
                  when(noteOrder){
                      is NoteOrder.Title-> notes.sortedBy { it.title.lowercase() }
                      is NoteOrder.Date->notes.sortedBy { it.timestamp }
                      is NoteOrder.Color->notes.sortedBy { it.color }
                  }
              }
              is OrderType.Descending->{
                  when(noteOrder){
                      is NoteOrder.Title-> notes.sortedByDescending { it.title.lowercase() }
                      is NoteOrder.Date->notes.sortedByDescending { it.timestamp }
                      is NoteOrder.Color->notes.sortedByDescending { it.color }
                  }
              }
          }
      }
    }
}