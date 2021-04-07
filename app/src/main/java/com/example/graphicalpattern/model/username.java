package com.example.graphicalpattern.model;

import android.content.Context;

import io.paperdb.Paper;

public class username {
    private String username="USER_NAME";
    private String password="PSW";
    public boolean isFirstTime=true;

    public username(Context context) {
        Paper.init(context);
    }

    public String getUsername() {
        return Paper.book().read(username);
    }

    public void setUsername(String USN) {
        Paper.book().write(username,USN);
    }

    public String getPassword() {
        return Paper.book().read(password);
    }

    public void setPassword(String PASS) {
        Paper.book().write(password,PASS);
    }

    public Boolean getFirstTime() {
        return isFirstTime;
    }

    public void setFirstTime(Boolean firstTime) {
        isFirstTime = firstTime;
    }

    public  Boolean isRight(String usn,String psw){
        return psw.equals(getPassword()) && usn.equals(getUsername());
    }
}
