package com.example.quicknote.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknote.data.Note
import com.example.quicknote.data.NoteDatabase
import com.example.quicknote.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : ViewModel() {

    private val repository: NoteRepository
    private var _noteState = MutableStateFlow<List<Note>>(emptyList())
    val subjectState: StateFlow<List<Note>>
        get() = _noteState


    init {
        val db = NoteDatabase.getDatabase(application)
        val dao = db.noteDao()
        repository = NoteRepository(dao)

        fetchNotes()
    }

    private fun fetchNotes() {
        viewModelScope.launch {
            repository.getNotes().collect { notes ->
                _noteState.value = notes
            }
        }
    }

    fun addNote(note: Note){
        viewModelScope.launch {
            repository.add(note)
        }
    }

    fun editNote(title: String, noteTxt: String, index: Int){
        viewModelScope.launch {
            repository.update(title, noteTxt, index)
        }
    }

    fun deleteNote(id: Int){
        viewModelScope.launch {
            repository.delete(id)
        }
    }

    fun clear() {
        viewModelScope.launch {
            repository.clear()
        }
    }

}