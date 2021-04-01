package com.example.graphicalpattern;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

public class PatternPage extends AppCompatActivity {
    private Button btnForgotPattern;
    private Button btnCreatePattern;
    private String password;

    GridView gridView;

    String[] img = {"p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8", "p9"};
    public int[] arr = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_page);

        btnForgotPattern = (Button) findViewById(R.id.btnForgot);
        btnCreatePattern = (Button) findViewById(R.id.btnOkay);

        gridView = (GridView)findViewById(R.id.grid_view);

        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                password += position;
                Toast.makeText(getApplicationContext(), "Hello There" + password, Toast.LENGTH_SHORT).show();
            }
        });

        btnForgotPattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(PatternPage.this,ForgetPattern.class);
                startActivity(i1);
            }
        });

        btnCreatePattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(PatternPage.this,appList.class);
                startActivity(i4);
            }
        });

    }

}