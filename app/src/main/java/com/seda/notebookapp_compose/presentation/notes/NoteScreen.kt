package com.seda.notebookapp_compose.presentation.notes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ModeEdit
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults.containerColor
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.seda.notebookapp_compose.Screen
import com.seda.notebookapp_compose.domain.model.Note
import com.seda.notebookapp_compose.domain.util.NoteOrder
import com.seda.notebookapp_compose.domain.util.OrderType
import com.seda.notebookapp_compose.presentation.notes.components.NoteItem
import com.seda.notebookapp_compose.presentation.notes.components.OrderSection
import com.seda.notebookapp_compose.presentation.notes.components.RadioButton
import com.seda.notebookapp_compose.ui.theme.NoteBookAppComposeTheme
import java.nio.file.Files.delete

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    navController: NavController,
    viewModel: NotesViewModel= hiltViewModel()
) {
    val state = viewModel.state.value
    Scaffold(containerColor = MaterialTheme.colorScheme.primaryContainer, topBar = {
        TopAppBar(
            title = { Text(text = "NOTES") },
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            actions = {
                IconButton(
                    onClick = {
                        viewModel.onEvent(NotesEvent.ToggleOrderSection)

                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = "Sort"
                    )

                }
            }

        )
    },

        floatingActionButton = {
            FloatingActionButton(
                containerColor=MaterialTheme.colorScheme.tertiary,
                onClick = {
                    navController.navigate(Screen.AddEditNoteScreen.route)
                },

                ) {
                Icon(imageVector = Icons.Default.ModeEdit, contentDescription ="Icon")
            }
        }, floatingActionButtonPosition = FabPosition.End
    ){
        Column(modifier =  Modifier.padding(it)) {
            AnimatedVisibility (
                visible= state.isOrderSectionVisible,
                enter = fadeIn() + slideInHorizontally(),
                exit = fadeOut() + slideOutHorizontally()

            ) {
                      OrderSection(
                          modifier = Modifier
                              .fillMaxWidth()
                              .padding(16.dp),
                          noteOrder = state.noteOrder,
                          orderChange ={key->
                              viewModel.onEvent(NotesEvent.Order(key))
                          }
                      )
            }
            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(modifier=Modifier.fillMaxSize()){


                    items(state.notes) { note ->

                            NoteItem(
                                note = note,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate(
                                            Screen.AddEditNoteScreen.route +
                                                    "?noteId=${note.id}&noteColor=${note.color}"
                                        )
                                    },
                                onDeleteClick = {
                                    viewModel.onEvent(NotesEvent.DeleteNote(note))

                                }
                            )





                    }
                }

        }




        }
    }






