package com.example.notesapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("select * from note")
    public List<Note> getNotes();
    @Insert
    void addNote(Note note);
    @Delete
    void deleteNote(Note note);

}
