package com.example.quicknote.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.quicknote.Routes
import com.example.quicknote.viewModel.NoteViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(navCon: NavHostController, viewModel: NoteViewModel, index: Int) {

    val notes by viewModel.subjectState.collectAsStateWithLifecycle()
    var title by remember { mutableStateOf(notes[index].title) }
    var noteText by remember { mutableStateOf(notes[index].noteText) }

    Column(modifier = Modifier.padding(2.dp)) {
        TextField(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 10.dp)
                .weight(1f)
                .fillMaxWidth(),
            value = title,
            onValueChange = { title = it },
            label = { Text("Name") },
            textStyle = LocalTextStyle.current.copy( textAlign = TextAlign.Left, fontSize = 26.sp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(255, 238, 155, 255),
            )
        )
        TextField(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 10.dp)
                .weight(5f)
                .fillMaxWidth(),
            value = noteText,
            onValueChange = { noteText = it },
            label = { Text("Note") },
            textStyle = LocalTextStyle.current.copy( textAlign = TextAlign.Left, fontSize = 17.sp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(255, 238, 155, 255),
            )
        )


        Button(
            modifier = Modifier
                .weight(.5f)
                .padding(horizontal = 5.dp)
                .padding(vertical = 5.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor =  Color(0, 255, 0, 90)),
            onClick = {
                if (title.isNotBlank() && noteText.isNotBlank()) {
                    viewModel.editNote(title, noteText, notes[index].id)
                    title = ""
                    noteText = ""

                    navCon.navigate(Routes.Main.route)
                }
            }
        ) {
            Text(
                text = "EDIT",
                color = Color.Black)
        }

        Button(
            modifier = Modifier
                .weight(.5f)
                .padding(horizontal = 5.dp)
                .padding(vertical = 5.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            onClick = {
                viewModel.deleteNote(notes[index].id)
                title = ""
                noteText = ""

                navCon.navigate(Routes.Main.route)
            }
        ) {
            Text(text = "DELETE")
        }
    }
}

