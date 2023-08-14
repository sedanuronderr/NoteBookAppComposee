package com.seda.notebookapp_compose.presentation.notes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seda.notebookapp_compose.domain.util.NoteOrder
import com.seda.notebookapp_compose.domain.util.OrderType
import com.seda.notebookapp_compose.ui.theme.NoteBookAppComposeTheme

@Composable
fun OrderSection(
    modifier: Modifier,
    noteOrder: NoteOrder= NoteOrder.Date(OrderType.Descending),
    orderChange: (NoteOrder) -> Unit
) {

    Column(modifier=Modifier.padding(5.dp)) {
        Card(Modifier.fillMaxWidth(), shape = CardDefaults.elevatedShape, elevation = CardDefaults.cardElevation()) {
            Row(modifier.fillMaxWidth()) {
                RadioButton(
                    text = "Title",
                    onClick = { orderChange(NoteOrder.Title(noteOrder.orderType)) },
                    enabled = noteOrder is NoteOrder.Title
                )
                Spacer(modifier = Modifier.width(18.dp))
                RadioButton(
                    text = "Date",
                    onClick = { orderChange(NoteOrder.Date(noteOrder.orderType)) },
                    enabled = noteOrder is NoteOrder.Date
                )
                Spacer(modifier = Modifier.width(8.dp))
                RadioButton(
                    text = "Color",
                    onClick = { orderChange(NoteOrder.Color(noteOrder.orderType)) },
                    enabled = noteOrder is NoteOrder.Color
                )
            }
            Divider(color = MaterialTheme.colorScheme.onPrimaryContainer, thickness = 1.dp)
            Spacer(modifier = Modifier.height(18.dp))

            Row( modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(18.dp))

                RadioButton(
                    text = "Ascending",
                    onClick = { orderChange(noteOrder.copy(OrderType.Ascending)) },
                    enabled = noteOrder.orderType is OrderType.Ascending
                )
                Spacer(modifier = Modifier.width(18.dp))
                RadioButton(
                    text = "Descending",
                    onClick = { orderChange(noteOrder.copy(OrderType.Descending))},
                    enabled = noteOrder.orderType is OrderType.Descending
                )


            }
            Spacer(modifier = Modifier.height(18.dp))

        }
    }
    
}


