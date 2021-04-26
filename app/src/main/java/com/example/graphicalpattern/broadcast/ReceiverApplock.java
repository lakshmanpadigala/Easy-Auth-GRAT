package com.example.graphicalpattern.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.graphicalpattern.PatternPage;
import com.example.graphicalpattern.model.password;
import com.example.graphicalpattern.utils.Utils;

public class ReceiverApplock extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Utils utils = new Utils(context);
        String appRunning = utils.getLauncherTopApp();

        if(utils.isLock(appRunning)){

            if(!appRunning.equals(utils.getLastApp())){

                utils.clearLastApp();
                utils.setLastApp(appRunning);
//todo asal dhi ikayday undhi mitrama...:;^)
                Intent i = new Intent(context, PatternPage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("broadcast_receiver","broadcast_receiver");
                context.startActivity(i);
            }
        }
    }
}
