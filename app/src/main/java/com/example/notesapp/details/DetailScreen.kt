package com.example.notesapp.details

import androidx.compose.animation.animateColorAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.example.notesapp.Colors



@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel,
    noteId:String,
    onNavigate:() -> Unit
) {
    val detailUiState = detailViewModel?.detailUiState ?: DetailUiState()

    val isFormNotBlank = detailUiState.note.isNotBlank() && detailUiState.title.isNotBlank()

    val selectedColor by animateColorAsState(targetValue = Colors.colors[detailUiState.colorIndex])

    val isNoteIdNotBlank = noteId.isNotBlank()

    val icon = if (isFormNotBlank) Icons.Default.Refresh else Icons.Default.Check

    LaunchedEffect(key1 = Unit){
        if (isNoteIdNotBlank){
            detailViewModel.getNote(noteId)
        }else{
            detailViewModel.resetState()
        }
    }
    val scope = rememberCoroutineScope()




}