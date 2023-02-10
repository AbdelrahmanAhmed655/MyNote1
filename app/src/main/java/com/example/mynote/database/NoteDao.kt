package com.example.mynote.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mynote.models.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note:Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * From notes_table order by id ASC")
    fun getAllNotes():LiveData<List<Note>>

    @Query("UPDATE notes_table set title= :title,note= :note WHERE id= :id")
    suspend fun update(id:Int?,title:String?,note:String?)
}