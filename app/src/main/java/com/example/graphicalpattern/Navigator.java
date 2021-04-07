package com.example.graphicalpattern;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.graphicalpattern.model.password;

public class Navigator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);

        password MNavigator = new password(this);
        if(MNavigator.getFirst().equals("true")){
            Intent x=new Intent(Navigator.this,Register.class);
            startActivity(x);
            System.out.println("from navigator if condition!");
        }else{
            Intent y = new Intent(Navigator.this,PatternPage.class);
            startActivity(y);
            System.out.println("from navigator else condition!");
        }
        finish();
    }
}