package com.example.graphicalpattern.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graphicalpattern.R;
import com.example.graphicalpattern.ViewHolder.MyViewHolder;
import com.example.graphicalpattern.model.Myitem;
import com.example.graphicalpattern.utils.Utils;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context ctx;
    private List<Myitem> apps;

    private Utils utils;
    //todo
    //List<String> lockedApps = new ArrayList<>();

    //adapter_design_backend()
    //ctx in con
    //apps is appModels
    public MyAdapter(Context ctx, List<Myitem> apps) {
        this.ctx = ctx;
        this.apps = apps;
        this.utils = new Utils(ctx);
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

        String pk = apps.get(position).getPackageName();
        if(utils.isLock(pk)){
            holder.lock_icon.setImageResource(R.drawable.ic_baseline_lock_24);
        }else{
            holder.lock_icon.setImageResource(R.drawable.ic_baseline_lock_open_24);
            //lockedApps.add(apps.get(position).getPackageName());
        }

        //todo
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(utils.isLock(pk)){
                    utils.unlock(pk);
                    holder.lock_icon.setImageResource(R.drawable.ic_baseline_lock_open_24);
                    Toast.makeText(ctx,apps.get(position).getName()+" is unlocked",Toast.LENGTH_SHORT).show();
                }
                else{
                    utils.lock(pk);
                    holder.lock_icon.setImageResource(R.drawable.ic_baseline_lock_24);
                    Toast.makeText(ctx,apps.get(position).getName()+" is locked",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }



}
