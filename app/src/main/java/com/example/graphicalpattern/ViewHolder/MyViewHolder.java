package com.example.graphicalpattern.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graphicalpattern.Interface.AppOnClickListener;
import com.example.graphicalpattern.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    //MyViewHolder is adapter_design_backend class
    public TextView app_name;
    public ImageView app_icon,lock_icon;

    private AppOnClickListener listener;

    public void setListener(AppOnClickListener listener){
        this.listener = listener;
    }

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        app_icon = itemView.findViewById(R.id.app_icon);
        app_name = itemView.findViewById(R.id.app_name);
        lock_icon =itemView.findViewById(R.id.lock_app);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.selectApp(getAdapterPosition());
            }
        });
    }
}
