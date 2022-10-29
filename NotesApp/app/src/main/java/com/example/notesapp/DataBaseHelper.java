package com.example.notesapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = Note.class, exportSchema = false, version = 1)
public abstract class DataBaseHelper extends RoomDatabase {

    private static final String DB_NAME = "notes_db";
    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getInstance(Context context){
        if (instance==null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),DataBaseHelper.class,
                    DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract NoteDao mNoteDao();
}
