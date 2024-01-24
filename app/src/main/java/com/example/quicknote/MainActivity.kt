package com.example.quicknote

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quicknote.ui.screens.AddScreen
import com.example.quicknote.ui.screens.EditScreen
import com.example.quicknote.ui.screens.MainScreen
import com.example.quicknote.ui.theme.QuickNoteTheme
import com.example.quicknote.viewModel.NoteViewModel
import com.example.quicknote.viewModel.NoteViewModelProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickNoteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation(){
    val viewModel: NoteViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "NoteViewModel",
        NoteViewModelProvider(LocalContext.current.applicationContext as Application)
    )
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Main.route) {
        composable(Routes.Main.route) {
            MainScreen(navController, viewModel)
        }

        composable(Routes.Add.route){
            AddScreen(navController, viewModel)
        }

        composable(Routes.Edit.route+"/{index}")
        { navBackStackEntry ->
            val ind = navBackStackEntry.arguments?.getString("index")
            ind?.let { EditScreen(navController, viewModel, it.toInt()) }
        }
    }
}