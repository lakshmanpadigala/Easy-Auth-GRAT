package com.example.graphicalpattern;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.graphicalpattern.model.password;
import com.shuhart.stepview.StepView;

public class PatternPage extends AppCompatActivity {
    private Button btnForgotPattern;
    private Button btnOk;
    private String password="";
    GridView gridView;
    //
    StepView stepView;
    LinearLayout linearLayout;
    LinearLayout linearLayout_r;
    com.example.graphicalpattern.model.password Mpassword;
    String userPassword;
    TextView stateText;
    //



    //String[] img = {"p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8", "p9"};
    //public static String []img ={"img1","img2","img3","img4","img5","img6","img7","img8","img9"};
    public static int[] img;
    public int[] arr = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_page);

        //
        stepView = findViewById(R.id.stepview);
        linearLayout_r = findViewById(R.id.main_layout);
        linearLayout=findViewById(R.id.ll);
        Mpassword=new password(this);
        stateText=findViewById(R.id.state_text);
        stateText.setText(Mpassword.FIRST_USE);
        if(Mpassword.getPASSWORD_KEY()==null){
            linearLayout.setVisibility(View.GONE);
            stepView.setVisibility(View.VISIBLE);
            stepView.setStepsNumber(2);
            stepView.go(0,true);
        }else{
            linearLayout.setVisibility(View.VISIBLE);
            stepView.setVisibility(View.GONE);
            int BackgroundColor= ResourcesCompat.getColor(getResources(),R.color.GREY,null);
            linearLayout_r.setBackgroundColor(BackgroundColor);
            stateText.setTextColor(Color.WHITE);
        }
        //


        btnForgotPattern = (Button) findViewById(R.id.btnForgot);
        btnOk = (Button) findViewById(R.id.btnOkay);

        gridView = (GridView)findViewById(R.id.grid_view);

        gridView.setAdapter(new ImageAdapter(this));
        img = ImageAdapter.arrret();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //System.out.println("position:"+position);
                //System.out.println(img[0]);
                password += img[position];
                Toast.makeText(getApplicationContext(), "Hello There!" + password, Toast.LENGTH_SHORT).show();
            }
        });

        btnForgotPattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(PatternPage.this,ForgetPattern.class);
                startActivity(i1);
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                if(password.length()<4){
                    stateText.setText(Mpassword.SCHEMA_FAILED);
                    password="";
                    return;
                }
                if(Mpassword.getPASSWORD_KEY()==null){
                    if(Mpassword.getFirst().equals("true")){
                        userPassword=password;
                        Mpassword.setFirst("false");
                        stateText.setText(Mpassword.CONFIRM_PATTERN);
                        stepView.go(1,true);
                    }else{
                        if(userPassword.equals(password)){
                            Mpassword.setPASSWORD_KEY(password);
                            stateText.setText(Mpassword.PATTERN_SET);
                            stepView.done(true);
                            gotoAppList();
                        }else{
                            stateText.setText(Mpassword.PATTERN_SET);
                        }
                    }
                }else{
                    if(Mpassword.isCorrect(password)){
                        stateText.setText(Mpassword.PATTERN_SET);
                        gotoAppList();
                    }else{
                        stateText.setText(Mpassword.INCORRECT_PATTERN);
                    }
                }
                password="";
            }
        });
        //
    }

    @Override
    public void onBackPressed() {
        if(Mpassword.getPASSWORD_KEY()==null && !Mpassword.getFirst().equals("true")){
            stepView.go(0,true);
            Mpassword.setFirst("true");
            stateText.setText(Mpassword.FIRST_USE);

        }else{
            finish();
            super.onBackPressed();
        }
    }

    private void gotoAppList() {
        System.out.println("happy to see the applist!");
        Intent ia = new Intent(PatternPage.this,appList.class);
        startActivity(ia);
        finish();
    }

}