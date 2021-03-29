package com.example.graphicalpattern.utils;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;

import androidx.annotation.RequiresApi;

public class Utils {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean PermissionCheck(Context c){
        AppOpsManager opsManager = (AppOpsManager)c.getSystemService(Context.APP_OPS_SERVICE);
        int mode = opsManager.unsafeCheckOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,Process.myUid(),c.getPackageName());
        return mode== AppOpsManager.MODE_ALLOWED;
    }
}
