package com.example.znote.home.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.znote.home.presentation.ui.widgets.HomeAppBar
import com.example.znote.home.presentation.ui.widgets.ListView
import com.example.znote.home.presentation.ui.widgets.SearchField
import com.example.znote.home.presentation.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigator: NavHostController,
    viewModel: NoteViewModel = hiltViewModel(),
) {
    viewModel.loadNotes()

    var showDialog by remember { mutableStateOf(false) }

    // Your main screen content goes here
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    HomeAppBar()
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog = true
                },
                modifier = Modifier
                    .padding(16.dp)
                    .clip(shape = CircleShape),
                containerColor = Color.Black // Set your desired color
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.White
                )
            }
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchField()
            ListView()
        }

        // Dialog
        if (showDialog) {
            AddEditNoteDialog(

                onDismiss = { showDialog = false },
                fromAdd = true

            )
        }

    }
}



