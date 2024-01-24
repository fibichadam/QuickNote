package com.example.quicknote.repository

import com.example.quicknote.data.Note
import com.example.quicknote.data.NoteDao

class NoteRepository(private val noteDao: NoteDao) {
    fun getNotes() = noteDao.getNotes()
    suspend fun clear() = noteDao.deleteAll()
    suspend fun add(note: Note) = noteDao.insert(note)
    suspend fun delete(id: Int) = noteDao.delete(id)
    suspend fun update(title: String, noteTxt: String, index: Int) = noteDao.update(title, noteTxt, index)
}