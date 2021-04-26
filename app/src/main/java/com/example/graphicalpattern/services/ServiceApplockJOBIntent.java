package com.example.graphicalpattern.services;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.example.graphicalpattern.broadcast.ReceiverApplock;

public class ServiceApplockJOBIntent extends JobIntentService {

    public static final int JOB_ID = 15462;

    public static void enqueueWork(Context context,Intent work){
        enqueueWork(context,ServiceApplockJOBIntent.class,JOB_ID,work);
    }
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        runApplock();
    }

    @Override
    public void onDestroy() {
        BackgroundManager.getInstance().init(this).startAlarmManager();
        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        BackgroundManager.getInstance().init(this).startAlarmManager();
        super.onTaskRemoved(rootIntent);
    }

    public void runApplock(){

        long endTime = System.currentTimeMillis()+210;
        while(System.currentTimeMillis() < endTime){
            synchronized (this){
                try{
                    Intent intent = new Intent(this, ReceiverApplock.class);
                    sendBroadcast(intent);
                    wait(endTime-System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}