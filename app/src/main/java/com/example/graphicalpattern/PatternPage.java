package com.example.graphicalpattern;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatternPage extends AppCompatActivity {
    private Button btnForgotPattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_page);

        btnForgotPattern = (Button)findViewById(R.id.forgotPattern);
        btnForgotPattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PatternPage.this, ForgetPattern.class);
                startActivity(intent1);
            }
        });
    }
}