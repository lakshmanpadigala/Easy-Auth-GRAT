package com.example.graphicalpattern.model;
import android.app.Application;

public class Globals extends Application {
    private String Check="NEW";

    public String getData(){
        return this.Check;
    }

    public void setData(String c){
        this.Check=c;
    }
}
