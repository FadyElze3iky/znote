package com.example.znote

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.znote.home.presentation.ui.HomeScreen
import com.example.znote.note.NoteScreen

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home")
    {
        composable("home") {
            HomeScreen(navController)
        }
        composable("note") {
            NoteScreen(navController)
        }
    }
}