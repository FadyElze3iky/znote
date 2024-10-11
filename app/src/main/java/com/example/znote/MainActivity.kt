package com.example.znote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.znote.AppNavigator
import com.example.znote.home.presentation.viewmodel.NoteViewModel
import com.example.znote.screen.SplashScreen
import com.example.znote.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var startMainScreen by mutableStateOf(false)

        setContent {
            NoteAppTheme {
                if (startMainScreen) {
                    AppNavigator() // Show the main screen
                }
                else {
                    SplashScreen {
                        startMainScreen = true // Navigate to the main screen
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteAppTheme {
    }
}