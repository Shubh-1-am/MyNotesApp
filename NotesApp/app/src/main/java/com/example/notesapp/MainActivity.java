package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button mBtn_CreateNote;
    FloatingActionButton mFbtn_AddNote;
    RecyclerView mRecyclerNotes;
    DataBaseHelper mHelper;
    LinearLayout mLL_No_Notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout layout = findViewById(R.id.mainLayout);
        AnimationDrawable drawable = (AnimationDrawable) layout.getBackground();
        drawable.setEnterFadeDuration(2500);
        drawable.setExitFadeDuration(5000);
        drawable.start();
       // layout.setBackground(getResources().getDrawable(R.drawable.gradientlist));

        initVar();
        showNotes();

        mFbtn_AddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_note_layout);

                EditText Edt_title, Edt_Content;
                Button Btn_add;

                Edt_title = dialog.findViewById(R.id.edt_title);
                Edt_title.setTextColor(getResources().getColor(R.color.DarkBlack));

                Edt_Content = dialog.findViewById(R.id.edt_content);
                Edt_Content.setTextColor(getResources().getColor(R.color.black));
                Btn_add =   dialog.findViewById(R.id.btn_add);

                Btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String title = Edt_title.getText().toString();
                        String content = Edt_Content.getText().toString();

                        if (!content.equals("")) {
                            mHelper.mNoteDao().addNote(new Note(title,content));
                            showNotes();
                            dialog.dismiss();

                        } else {
                            Toast.makeText(MainActivity.this, "Please Enter Something..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.show();

            }
        });

        mBtn_CreateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFbtn_AddNote.performClick();
            }
        });

    }

    public void showNotes() {
        ArrayList<Note> arrNotes = (ArrayList<Note>) mHelper.mNoteDao().getNotes();
        if (arrNotes.size()>0) {
            mRecyclerNotes.setVisibility(View.VISIBLE);
            mLL_No_Notes.setVisibility(View.GONE);

            mRecyclerNotes.setAdapter(new CustomAdapter(this,arrNotes,mHelper));
        }
        else {
            mLL_No_Notes.setVisibility(View.VISIBLE);
            mRecyclerNotes.setVisibility(View.GONE);
        }

    }

    private void initVar() {

        mBtn_CreateNote = findViewById(R.id.btn_createNotes);
        mFbtn_AddNote = findViewById(R.id.fab_add);
        mRecyclerNotes = findViewById(R.id.rv_notes);
        mLL_No_Notes = findViewById(R.id.ll_no_notes);

        mRecyclerNotes.setLayoutManager(new GridLayoutManager(this,2));
        mHelper = DataBaseHelper.getInstance(this);
    }
}