package com.seda.notebookapp_compose.presentation.notes.components

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seda.notebookapp_compose.domain.model.Note
import com.seda.notebookapp_compose.presentation.notes.NoteScreen
import com.seda.notebookapp_compose.ui.theme.NoteBookAppComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    onDeleteClick:()->Unit
    ) {
    Card(
        onClick = { /* Do something */ },
        modifier = Modifier.fillMaxSize().padding(10.dp).padding(top = 5.dp, end = 5.dp),
        elevation= CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(containerColor = Color(note.color)), shape = RoundedCornerShape(20.dp,3.dp,20.dp,20.dp)

    ) {
        Box(Modifier.fillMaxSize()) {
          Column(
              modifier
                  .fillMaxSize()
                  .padding(16.dp)
                  .padding(end = 22.dp)) {

              Text(
                  text = note.title,
                  style = MaterialTheme.typography.titleLarge,
                  color = MaterialTheme.colorScheme.onSurface,
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis

              )
              Spacer(modifier = modifier.height(8.dp))
              Text(
                  text = note.content,
                  style = MaterialTheme.typography.bodyLarge,
                  color = MaterialTheme.colorScheme.onSurface,
                  maxLines = 10,
                  overflow = TextOverflow.Ellipsis
              )

          }
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete note",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

        }
    }


}

