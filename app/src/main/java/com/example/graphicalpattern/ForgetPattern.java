package com.example.graphicalpattern;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForgetPattern extends AppCompatActivity {
    private Button ValidateBtn;
    private Button PatternBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pattern);
        ValidateBtn = (Button)findViewById(R.id.btnValidate);
        PatternBtn = (Button)findViewById(R.id.btnPattern);
        PatternBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ForgetPattern.this, PatternPage.class);
                startActivity(intent2);
            }
        });

        ValidateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(ForgetPattern.this,PatternPage.class);
                startActivity(intent3);
            }
        });
    }





}