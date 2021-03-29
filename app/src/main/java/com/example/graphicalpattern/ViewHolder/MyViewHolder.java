package com.example.graphicalpattern.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graphicalpattern.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView app_name;
    public ImageView app_icon,lock_icon;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        app_icon = itemView.findViewById(R.id.app_icon);
        app_name = itemView.findViewById(R.id.app_name);
        lock_icon =itemView.findViewById(R.id.lock_app);

    }
}
