package com.example.graphicalpattern.model;

import android.content.Context;

import io.paperdb.Paper;

public class password {
    private String PASSWORD_KEY = "PASSWORD KEY";
    public String PATTERN_SET = "PATTERN SET";
    public String CONFIRM_PATTERN = "Draw the pattern again to confirm";
    public String INCORRECT_PATTERN = "Please try again ";
    public String FIRST_USE = "Draw an unlock pattern please ";
    public String SCHEMA_FAILED = "You must at least connect 4 dots";
    public boolean isFirst=true;

    public password(Context context){
        Paper.init(context);
        //List<String> allKeys=Paper.book().getAllKeys();
        //if(allKeys.size()==0){
        Paper.book().write("isFirst", true);
        //}else {
          //  Paper.book().write(isFirst,"false");
       // }

        //System.out.println("From Paper init!");
        //System.out.println((String) Paper.book().read(isFirst));
    }

    public String getPASSWORD_KEY() {
        return Paper.book().read(PASSWORD_KEY);
    }

    public void setPASSWORD_KEY(String PASS) {
        //Paper.book().write("isFirst", false);
        Paper.book().write(PASSWORD_KEY,PASS);
    }
    public void onDelete(){
        Paper.book().delete(PASSWORD_KEY);
    }

    public Boolean getFirst() {
        //return isFirst;
        return Paper.book().read("isFirst");
    }

    public void setFirst( Boolean first) {
        //isFirst = first;
        Paper.book().write("isFirst", first);
        System.out.println("From setfirst function..!");
    }
    public Boolean isCorrect(String PASS){
        return PASS.equals(getPASSWORD_KEY());
    }
}





















