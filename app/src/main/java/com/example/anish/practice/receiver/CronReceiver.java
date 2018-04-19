package com.example.anish.practice.receiver;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.example.anish.practice.services.CronService;

/**
 * Created by Anish on 07-07-2017.
 */

public class CronReceiver extends WakefulBroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Receiver","from receiver");
        //context.startService(new Intent(context,CronService.class));
        startWakefulService(context,new Intent(context,CronService.class));
    }
}
