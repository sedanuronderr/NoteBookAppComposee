package com.seda.notebookapp_compose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seda.notebookapp_compose.R
import com.seda.notebookapp_compose.ui.theme.acikmavi
import com.seda.notebookapp_compose.ui.theme.mavi
import com.seda.notebookapp_compose.ui.theme.md_theme_light_onPrimary
import com.seda.notebookapp_compose.ui.theme.md_theme_light_onSecondaryContainer
import com.seda.notebookapp_compose.ui.theme.md_theme_light_primary
import com.seda.notebookapp_compose.ui.theme.md_theme_light_primaryContainer
import com.seda.notebookapp_compose.ui.theme.md_theme_light_secondary
import com.seda.notebookapp_compose.ui.theme.mor
import com.seda.notebookapp_compose.ui.theme.orange
import com.seda.notebookapp_compose.ui.theme.pembe

@Entity
data class Note(
    val title:String,
    val content:String,
    val timestamp:Long,
    val color:Int,
    @PrimaryKey val id:Int?=null

){
    companion object{
        val noteColors = listOf(
            pembe, orange,
            mor,mavi,
         acikmavi)
    }
}
