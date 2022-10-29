package com.example.notesapp;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView txtTitle, txtContent;
    LinearLayout llRow;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTitle = itemView.findViewById(R.id.tv_row_title);
        txtContent = itemView.findViewById(R.id.tv_row_content);
        llRow = itemView.findViewById(R.id.ll_row);
    }
}
