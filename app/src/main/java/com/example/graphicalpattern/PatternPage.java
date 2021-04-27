package com.example.graphicalpattern;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.graphicalpattern.model.password;
import com.example.graphicalpattern.services.BackgroundManager;
import com.example.graphicalpattern.utils.Utils;
import com.shuhart.stepview.StepView;

import io.paperdb.Paper;

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
    //Global declaration.....
    //Globals g1 = (Globals)getApplication();


    //String[] img = {"p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8", "p9"};
    //public static String []img ={"img1","img2","img3","img4","img5","img6","img7","img8","img9"};
    public static int[] img;
    public static int[] arr;
    public int[] panda= {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9};
    public int[] random={R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,R.drawable.p6,R.drawable.p7,R.drawable.p8,R.drawable.p9};
    public int[] blurred={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_page);

        //todo in pattern page

        BackgroundManager.getInstance().init(this).startService();
        initIconeApp();

        Paper.init(this);
        if(Paper.book().read("count")==null){
            Paper.book().write("count",3);
        }
        final int[] count = {Paper.book().read("count")};
        System.out.println("on create ki vachanu");
        //
        stepView = findViewById(R.id.stepview);
        linearLayout_r = findViewById(R.id.main_layout);
        linearLayout=findViewById(R.id.ll);
        Mpassword=new password(this);
        stateText=findViewById(R.id.state_text);




        btnForgotPattern = (Button) findViewById(R.id.btnForgot);
        btnOk = (Button) findViewById(R.id.btnOkay);
        /////////btnOk.setEnabled(true);
        if(Paper.book().read("count").equals(0)){
            stateText.setText(Mpassword.INCORRECT_PATTERN3);
            btnOk.setEnabled(false);
            System.out.println(Paper.book().read("count")+":count 0 from here");
        }
        gridView = (GridView)findViewById(R.id.grid_view);
        ImageAdapter imgadp = new ImageAdapter(this);

        String category=Paper.book().read("category");
        System.out.println(category+" .see here for category!");
        if(category.equals("Panda")){
            arr = panda;
            imgadp.getType(panda);
        }else if(category.equals("Random")){
            arr = random;
            imgadp.getType(random);
        }else if(category.equals("Blurred")){
            arr = blurred;
            imgadp.getType(blurred);
        }
        imgadp.swap();
        gridView.setAdapter(imgadp);
        img = imgadp.arret();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //System.out.println("position:"+position);
                //System.out.println(img[0]);
                password += img[position];
                System.out.println("password:"+password);
                //Toast.makeText(getApplicationContext(), "Hello There!" + password, Toast.LENGTH_SHORT).show();
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
                if(Paper.book().read("count").equals(3) || Paper.book().read("count").equals(2)||Paper.book().read("count").equals(1)){
                    btnOk.setEnabled(true);
                }else{
                    btnOk.setEnabled(false);
                }
                if(password.length()<30){
                    stateText.setText(Mpassword.SCHEMA_FAILED);
                    password="";
                    return;
                }
                if(Mpassword.getPASSWORD_KEY()==null){
                    //g1.getData().equals("NEW")
                    //error:  Mpassword.getFirst().equals("true")
                    if(Mpassword.getFirst()==null){
                        userPassword=password;
                        Mpassword.setFirst("false");
                        //g1.setData("OLD");
                        stateText.setText(Mpassword.CONFIRM_PATTERN);
                        stepView.go(1,true);
                        imgadp.swap();
                        gridView.setAdapter(imgadp);

                    }else{
                        if(userPassword.equals(password)){
                            Mpassword.setPASSWORD_KEY(password);
                            Paper.book().write("count",3);
                            stateText.setText(Mpassword.PATTERN_SET);
                            stepView.done(true);
                            gotoAppList();
                            finish();
                        }else{
                            stateText.setText(Mpassword.PATTERN_NOT_SAME);
                        }
                    }
                }else{
                    if(Mpassword.isCorrect(password)){
                        stateText.setText(Mpassword.PATTERN_SET);
                        System.out.println("gotoapplist");
                        gotoAppList();
                        finish();
                    }else{
                        if(count[0] ==3){
                            stateText.setText(Mpassword.INCORRECT_PATTERN1);
                            count[0]--;
                            Paper.book().write("count",count[0]);
                        }else if(count[0] == 2){
                            stateText.setText(Mpassword.INCORRECT_PATTERN2);
                            count[0]--;
                            Paper.book().write("count",count[0]);
                        }else if(count[0] == 1){
                            stateText.setText(Mpassword.INCORRECT_PATTERN3);
                            count[0]--;
                            Paper.book().write("count",count[0]);
                            //btnOk.setEnabled(false);
                        }
                    }
                }
                password="";
            }
        });
        //
    }

    private void initIconeApp() {
        if(getIntent().getStringExtra("broadcast_reciever") != null){
            ImageView icone = findViewById(R.id.app_icon);
            String current_app = new Utils(this).getLastApp();
            ApplicationInfo applicationInfo = null;
            try{
                applicationInfo = getPackageManager().getApplicationInfo(current_app,0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            icone.setImageDrawable(applicationInfo.loadIcon(getPackageManager()));
        }
    }

    @Override
    protected void onResume() {
        System.out.println("Resume is happening!"+"okay...!");
        //System.out.println(Mpassword.getPASSWORD_KEY());
        stateText.setText(Mpassword.FIRST_USE);
        btnOk.setEnabled(true);
        if(Mpassword.getPASSWORD_KEY()==null){
            System.out.println("From Here!");

            linearLayout.setVisibility(View.GONE);
            stepView.setVisibility(View.VISIBLE);
            stepView.setStepsNumber(2);
            stepView.go(0,true);
        }else{
            System.out.println("Not From there!");
            linearLayout.setVisibility(View.VISIBLE);
            stepView.setVisibility(View.GONE);
            int BackgroundColor= ResourcesCompat.getColor(getResources(),R.color.GREY,null);
            linearLayout_r.setBackgroundColor(BackgroundColor);
            stateText.setTextColor(Color.WHITE);
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        //g1.getData().equals("OLD")
        if(Mpassword.getPASSWORD_KEY()==null && !Mpassword.getFirst().equals("true")){
            stepView.go(0,true);
            Mpassword.setFirst("true");
            //g1.setData("NEW");
            stateText.setText(Mpassword.FIRST_USE);
        }else{
            startCurrentHomePackage();
            finish();
            super.onBackPressed();
        }
    }

    private void startCurrentHomePackage() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo resolveInfo = getPackageManager().resolveActivity(intent,PackageManager.MATCH_DEFAULT_ONLY);

        ActivityInfo activityInfo = resolveInfo.activityInfo;
        ComponentName componentName = new ComponentName(activityInfo.applicationInfo.packageName,activityInfo.name);
        startActivity(intent);

        new Utils(this).clearLastApp();
    }

    private void gotoAppList() {
        System.out.println("happy to see the applist!");
        if(getIntent().getStringExtra("broadcast_receiver") == null){
            Intent ia = new Intent(PatternPage.this,appList.class);
            startActivity(ia);
        }
        finish();
    }

}