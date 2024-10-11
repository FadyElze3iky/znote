package com.example.znote.home.presentation.ui.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.znote.home.presentation.ui.AddEditNoteDialog
import com.example.znote.home.presentation.viewmodel.NoteViewModel
import com.example.znote.ui.theme.Gray
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListView(viewModel: NoteViewModel = hiltViewModel()) {

    val notes = viewModel.notes
    var showDialog by remember { mutableStateOf(false) }
    var currentIndex by remember {
        mutableIntStateOf(0)
    }
    var deleteDialog by remember { mutableStateOf(false) }

    // Create a LazyColumn to enable scrolling
    if (notes.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(notes.chunked(2)) { notePair ->
                Row(modifier = Modifier.fillMaxWidth()) {

                    val width = remember {
                        Random.nextInt(130, 250).dp
                    }
                    // First item in the row
                    ListItem(
                        item = notePair[0],
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(shape = RoundedCornerShape(20.dp))
                            .combinedClickable(
                                onClick = {
                                    currentIndex = notes.indexOf(notePair[0])
                                    showDialog = true
                                },
                                onLongClick = {
                                    currentIndex = notes.indexOf(notePair[0])
                                    deleteDialog = true
                                }
                            ),
                        width = width

                    )

                    // Check if there's a second item in the row
                    if (notePair.size > 1) {
                        ListItem(
                            item = notePair[1],
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(1f)
                                .clip(shape = RoundedCornerShape(20.dp))

                                .combinedClickable(
                                    onClick = {
                                        currentIndex = notes.indexOf(notePair[1])
                                        showDialog = true
                                    },
                                    onLongClick = {
                                        currentIndex = notes.indexOf(notePair[1])
                                        deleteDialog = true

                                    }
                                ),

                            width = width

                        )

                    }
                }
            }
        }
    }
    if (deleteDialog) {
        AlertDialog(
            modifier = Modifier
                .border(
                    width = 3.dp,
                    color = Color(notes[currentIndex].color),
                    shape = RoundedCornerShape(20)
                ),
            onDismissRequest = { deleteDialog = false },
            containerColor = Color.White,
            confirmButton = {

                TextButton(onClick = {
                    viewModel.deleteNoteById(notes[currentIndex].id)
                    deleteDialog = false
                }) {
                    Text(text = "Delete")
                }
            },

            title = {
                val splitString = notes[currentIndex].content.split(" ")
                val label =
                    if (splitString.size > 1) "${splitString[0]} ${splitString[1]}".uppercase(Locale.getDefault())
                    else notes[currentIndex].content.uppercase(
                        Locale.getDefault()
                    )

                Text(text = "Delete this note", fontSize = 16.sp, fontWeight = FontWeight.Light)

            }
        )
    }
    if (showDialog) {
        AddEditNoteDialog(
            note = notes[currentIndex],
            onDismiss = { showDialog = false },
            fromAdd = false

        )
    }

}

@Composable
fun ListItem(
    item: com.example.znote.home.domain.Note,
    modifier: Modifier,
    width: Dp,
) {

    Box(
        modifier = modifier

            .background(color = Color(item.color)) // Use the persisted random color
            .width(width)  // Use the persisted random width
            .height(200.dp) // Use the fixed height for uniformity

    ) {

        Column(Modifier.fillMaxSize()) {
            val date = Date(item.date.toLong())

            val newDateFormat = SimpleDateFormat("dd MMM hh:mm a", Locale.ENGLISH)
            val newFormattedDate = date.let { newDateFormat.format(it) }

            val splitString = item.content.split(" ")
            val label =
                if (splitString.size > 1) "${splitString[0]} ${splitString[1]}".uppercase(Locale.getDefault())
                else item.content.uppercase(
                    Locale.getDefault()
                )
            Text(
                text = label,
                modifier = Modifier.padding(start = 15.dp, top = 20.dp, end = 15.dp),
                overflow = TextOverflow.Ellipsis, // Show ellipsis if text is too long
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                color = Color.DarkGray,
                maxLines = 1
            )
            Text(
                text = item.content,
                modifier = Modifier
                    .padding(start = 15.dp, end = 10.dp, bottom = 10.dp)
                    .weight(1f),
                maxLines = 7,
                overflow = TextOverflow.Ellipsis // Show ellipsis if text is too long
                , color = Gray
            )
            Text(
                text = newFormattedDate ?: "",
                modifier = Modifier
                    .padding(end = 15.dp, bottom = 10.dp)
                    .align(Alignment.End),
                fontSize = 12.sp,
                color = Gray
            )
        }
    }
}


