package com.example.graphicalpattern.utils;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;

import java.util.List;

import io.paperdb.Paper;

import static android.app.AppOpsManager.MODE_ALLOWED;

public class Utils {
    private  String EXTRA_LAST_APP = "EXTRA_LAST_APP";
    private Context ctx;

    public Utils(Context ctx) {
        this.ctx = ctx;
        Paper.init(ctx);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean PermissionCheck(Context c){
        AppOpsManager opsManager = (AppOpsManager)c.getSystemService(Context.APP_OPS_SERVICE);
        int mode = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            mode = opsManager.unsafeCheckOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, Process.myUid(),c.getPackageName());
        }
        return mode == MODE_ALLOWED;
    }


    public boolean isLock(String packageName) {
        return Paper.book().read(packageName) != null;
    }

    public void lock(String pk){
        Paper.book().write(pk,"lock");
        //todo pk pk paper write...
    }

    public void unlock(String pk){
        Paper.book().delete(pk);
    }

    public void setLastApp(String pk){
        Paper.book().write("EXTRA_LAST_APP",pk);
    }

    public String getLastApp() {
        return Paper.book().read(EXTRA_LAST_APP);
    }

    public void clearLastApp() {
        Paper.book().delete(EXTRA_LAST_APP);
    }

    UsageStatsManager usageStatsManager;

    public String getLauncherTopApp() {
        ActivityManager manager = (ActivityManager)ctx.getSystemService(Context.ACTIVITY_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            usageStatsManager = (UsageStatsManager)ctx.getSystemService(Context.USAGE_STATS_SERVICE);
        }

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            List<ActivityManager.RunningTaskInfo> taskInfoList = manager.getRunningTasks(1);
            if(null != taskInfoList && !taskInfoList.isEmpty()){
                return taskInfoList.get(0).topActivity.getPackageName();
            }
        }else{
            long endTime = System.currentTimeMillis();
            long beginTime = endTime-10000;
            String result = "";
            UsageEvents.Event event = new UsageEvents.Event();
            UsageEvents usageEvents = usageStatsManager.queryEvents(beginTime,endTime);

            while(usageEvents.hasNextEvent()){
                usageEvents.getNextEvent(event);
                if(event.getEventType() == UsageEvents.Event.MOVE_TO_FOREGROUND){
                    result = event.getPackageName();
                }
            }

            if(!TextUtils.isEmpty(result)){
                return result;
            }

        }

        return "";
    }
}
