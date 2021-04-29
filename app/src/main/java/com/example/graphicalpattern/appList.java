package com.example.graphicalpattern;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graphicalpattern.adapter.MyAdapter;
import com.example.graphicalpattern.model.Myitem;
import com.example.graphicalpattern.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class appList extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;

    LinearLayout layout_permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        initView();
        initToolbar();
    }

    /*@Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }*/

    private void initView() {
        RecyclerView recyclerView=findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter adapter = new MyAdapter(this,getApplications());
        recyclerView.setAdapter(adapter);


        layout_permission = findViewById(R.id.layout_permission);
        //todo
        //adapter.notifyDataSetChanged();
    }

    private List<Myitem> getApplications() {
        List<Myitem> items=new ArrayList<>();
        PackageManager manager = getPackageManager();

        Intent i = new Intent(Intent.ACTION_MAIN,null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> infos = manager.queryIntentActivities(i,0);
        for(ResolveInfo resolveInfo: infos){
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            items.add(new Myitem(activityInfo.loadIcon(manager),activityInfo.loadLabel(manager).toString(),activityInfo.applicationInfo.packageName));
        }
        return items;
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Applications");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home)
            finish();
        else if (item.getItemId() == R.id.logout) {
            //todo logout
            //Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),LoginFirebase.class));
            return true;
        }
        return true;
    }

    public void set_permission(View view) {
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        //}

    }

    @Override
    protected void onResume() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            if(Utils.PermissionCheck(this)){
                layout_permission.setVisibility(View.GONE);
            }else{
                layout_permission.setVisibility((View.VISIBLE));
            }
        }
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu,menu);
        return true;
    }

}