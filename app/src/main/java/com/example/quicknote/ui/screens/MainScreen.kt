package com.example.quicknote.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.quicknote.Routes
import com.example.quicknote.viewModel.NoteViewModel


@Composable
fun MainScreen(navCon: NavHostController, viewModel: NoteViewModel) {

    val notes by viewModel.subjectState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(2.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(notes.size) {
                Row {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .clickable { navCon.navigate(Routes.Edit.route + "/$it") }
                            .graphicsLayer {
                                shape = RoundedCornerShape(10.dp)
                                clip = true
                            }
                            .background(Color(255, 238, 155, 255))
                            .wrapContentHeight()
                    ) {
                        Text(
                            text = notes[it].title,
                            fontSize = 26.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .padding(start = 15.dp, top = 10.dp, bottom = 10.dp)
                        )
                    }
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp, end = 4.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor =  Color(0, 255, 0, 90)),
            onClick = { navCon.navigate(Routes.Add.route) }
        ) {
            Text(
                text = "Add",
                color = Color.Black)
        }
    }
}