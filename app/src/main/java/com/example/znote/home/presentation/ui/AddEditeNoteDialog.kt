package com.example.znote.home.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.znote.home.domain.Note
import com.example.znote.home.presentation.viewmodel.NoteViewModel

@Composable
fun AddEditNoteDialog(
    note: Note? = null,
    onDismiss: () -> Unit,
    viewModel: NoteViewModel = hiltViewModel(),
    fromAdd: Boolean,

    ) {
    var content by remember { mutableStateOf(note?.content ?: "") }
    var size by remember {
        mutableFloatStateOf(.7f)
    }
    var color by remember {
        mutableStateOf(Color(note?.color ?: 0x44650D21))
    }

    AlertDialog(

        onDismissRequest = onDismiss,
        containerColor = Color.White,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.shadow(15.dp, clip = true, shape = RoundedCornerShape(15.dp)),

        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DecorationLabel(note)
                DecorationLabel(note)
                DecorationLabel(note)
                DecorationLabel(note)
                DecorationLabel(note)

            }

        },
        confirmButton = {
            TextButton(onClick = {

                if (fromAdd) {
                    if (content.isNotEmpty()) {
                        viewModel.addNote(content = content)

                    }
                    onDismiss()
                }
                else {
                    if (content != note?.content) {
                        note?.content = content
                        note?.color = color.toArgb()
                        viewModel.updateNote(note!!)
                    }
                    else if (note.color != color.toArgb()) {
                        note.color = color.toArgb()
                        viewModel.updateNote(note)

                    }

                    onDismiss()
                }

            }) {
                Text("Save")
            }
        },

        text = {

            BasicTextField(
                value = content,
                onValueChange = {
                    content = it
                    size = 1f

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(size),
                cursorBrush = SolidColor(value = Color(note?.color ?: 0x44650D21)),
                textStyle = TextStyle(fontSize = 20.sp)
            )

        }
    )
}

@Composable
private fun DecorationLabel(note: Note?) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(4.dp))
            .background(color = Color(note?.color ?: 0x44650D21))
            .width(40.dp)
            .height(
                10.dp
            )
            .padding(10.dp)

    )
}
