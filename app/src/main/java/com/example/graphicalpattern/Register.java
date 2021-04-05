package com.example.graphicalpattern;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.graphicalpattern.model.username;

public class Register extends AppCompatActivity {
    private EditText uName;
    private EditText psw;
    //username MRegister = new username(this);

    private EditText cpsw;
    private Button CreatePattern;
    private Spinner category;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username MRegister = new username(this);

        uName = (EditText)findViewById(R.id.etUserName);
        psw = (EditText)findViewById(R.id.etPassword);
        cpsw = (EditText)findViewById(R.id.etPassword1);
        CreatePattern = (Button)findViewById(R.id.btnCreatePattern);

        category = (Spinner)findViewById(R.id.spinner);
        //Paper.init(this);

        CreatePattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Register.this,"button pressed!",Toast.LENGTH_SHORT).show();
                Boolean vali=validate();
                System.out.println("validate"+vali);
                if(vali){
                    MRegister.setUsername(uName.getText().toString());
                    MRegister.setPassword(psw.getText().toString());
                    MRegister.setFirstTime(false);
                    openActivity();
                    finish();
                }

            }
        });
    }

    private boolean validate() {
        String pass=psw.getText().toString();
        String cpass=cpsw.getText().toString();
        String un=uName.getText().toString();
        System.out.println("validate...!");
        System.out.println("pass:"+pass);
        System.out.println("Cpass:"+cpass);
        if(pass.length() == 0 || cpass.length()==0 || un.length()==0 ){
            Toast.makeText(Register.this,"No column should be Empty!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!pass.equals(cpass)){
            Toast.makeText(Register.this,"password not matching",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void openActivity(){
        Intent intent = new Intent(this,PatternPage.class);
        startActivity(intent);
    }
}