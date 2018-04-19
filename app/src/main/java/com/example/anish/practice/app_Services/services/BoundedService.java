package com.example.anish.practice.app_Services.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Anish on 07-11-2017.
 */

public class BoundedService extends Service {
    String TAG = "BoundedService";

    public class ServiceBinder extends Binder{

    public BoundedService getService (){
        return BoundedService.this;
    }

    }

    IBinder iBinder = new ServiceBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"from onBind()");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"from onUnBind()");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"from onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"from onDestroy()");
        super.onDestroy();
    }

    public String getMessage(){
        return "Hi from BoundedService";
    }
}
