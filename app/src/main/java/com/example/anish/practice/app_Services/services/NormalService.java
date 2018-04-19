package com.example.anish.practice.app_Services.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Anish on 07-11-2017.
 */

public class NormalService extends Service{
    String TAG = "NormalService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"from onStartCommand");
        Log.i(TAG,"Thread:"+Thread.currentThread().getId());
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"from onDestroy");
        super.onDestroy();
    }
}
