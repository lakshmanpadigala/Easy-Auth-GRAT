package com.example.graphicalpattern;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private EditText uName;
    private EditText password;
    private EditText password1;
    private Button CreatePattern;
    private Spinner category;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        uName = (EditText)findViewById(R.id.etUserName);
        password = (EditText)findViewById(R.id.etPassword);
        password1 = (EditText)findViewById(R.id.etPassword1);
        CreatePattern = (Button)findViewById(R.id.btnCreatePattern);
        category = (Spinner)findViewById(R.id.spinner);

        /*
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        */



        CreatePattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Register.this,"button pressed!",Toast.LENGTH_SHORT).show();
                openActivity();
                /*
                String a=password.getText().toString()+uName.getText().toString();
                Toast.makeText(MainActivity.this,a,Toast.LENGTH_SHORT).show();
                validate(password.getText().toString(), password1.getText().toString(),uName.getText().toString());
                */
            }
        });
    }
    public void validate(String password,String password1, String uName){
        String a=password+password1;
        Toast.makeText(Register.this,a,Toast.LENGTH_SHORT).show();
        progressDialog.setMessage("You can subscribe to my channel until you are verified!");
        progressDialog.show();
        if(password.equals(password1) && !uName.isEmpty() ){
            //CreatePattern.setEnabled(true);

            Toast.makeText(Register.this,"Registration Sucessful!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Register.this, PatternPage.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(Register.this,"Make sure you Re-Enter Password Same",Toast.LENGTH_SHORT).show();
            //CreatePattern.setEnabled(false);
        }
    }
    public void openActivity(){
        Intent intent = new Intent(this,PatternPage.class);
        startActivity(intent);
    }
}