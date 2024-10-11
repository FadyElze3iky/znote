package com.example.znote.home.presentation.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.znote.R
import com.example.znote.home.presentation.viewmodel.NoteViewModel
import com.example.znote.ui.theme.LightGray

@Composable
fun SearchField(viewModel: NoteViewModel = hiltViewModel()) {

    var value by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(.9f),
        value = value,
        onValueChange = {

            value = it
            viewModel.searchNote(it)

        },
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.vector),
                contentDescription = "Search icon",
                modifier = Modifier.scale(2f)
            )
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                style = TextStyle(color = Color.Gray)
            )
        },
        shape = RoundedCornerShape(25),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = LightGray
        ),
        minLines = 1
    )
}