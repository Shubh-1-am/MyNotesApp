package com.example.notesapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    Context mContext;
    ArrayList<Note> mList = new ArrayList<>();
    DataBaseHelper mHelper;

    public CustomAdapter(Context context, ArrayList<Note> list, DataBaseHelper Helper) {
        mContext = context;
        mList = list;
        mHelper = Helper;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(mContext).inflate(R.layout.note_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int i) {
        holder.txtTitle.setText(mList.get(i).getTitle());
        holder.txtContent.setText(mList.get(i).getContent());

        holder.llRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                deleteItem(i);
                return true;
            }
        });
    }

    private void deleteItem(int pos) {
        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle("Delete")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mHelper.mNoteDao().deleteNote(new Note(mList.get(pos).getId(),
                                mList.get(pos).getTitle(),mList.get(pos).getContent()));
                        ((MainActivity)mContext).showNotes();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
