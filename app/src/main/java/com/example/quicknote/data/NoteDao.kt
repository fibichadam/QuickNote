package com.example.quicknote.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table ORDER BY title ASC")
    fun getNotes(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Query("DELETE FROM note_table WHERE id = :index")
    suspend fun delete(index: Int)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()

    @Query("UPDATE note_table SET title = :title, noteText = :noteTxt WHERE id = :index")
    suspend fun update(title: String, noteTxt: String, index: Int)
}