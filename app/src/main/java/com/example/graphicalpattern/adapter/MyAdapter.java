package com.example.graphicalpattern.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graphicalpattern.R;
import com.example.graphicalpattern.ViewHolder.MyViewHolder;
import com.example.graphicalpattern.model.Myitem;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context ctx;
    private List<Myitem> apps;

    public MyAdapter(Context ctx, List<Myitem> apps) {
        this.ctx = ctx;
        this.apps = apps;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.my_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.app_name.setText(apps.get(position).getName());
        holder.app_icon.setImageDrawable(apps.get(position).getIcon());

    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

}
