package com.example.mynote.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mynote.database.NoteDataBase
import com.example.mynote.database.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application:Application):AndroidViewModel(application) {

    private val repository:NotesRepository
    val allNotes:LiveData<List<Note>>
    init {
        val dao=NoteDataBase.getDatabase(application).getNoteDeo()
        repository= NotesRepository(dao)
        allNotes=repository.allNotes
    }

    fun deleteNote(note:Note)= viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNote(note:Note)=viewModelScope.launch (Dispatchers.IO){
        repository.insert(note)
    }

    fun updateNote(note:Note)=viewModelScope.launch {
        repository.update(note)
    }

}