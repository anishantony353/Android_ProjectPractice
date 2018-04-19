package com.example.anish.practice.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.anish.practice.utility.Utility;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anish on 07-07-2017.
 */

public class CronService extends Service {
    Intent intentWork;
    Handler handler;
    Runnable runnable;
    PowerManager.WakeLock wakeLock;

//
//    public CronService() {
//        super("CronService");
//    }
//
//    @Override
//    protected void onHandleIntent(@Nullable Intent intent) {
//        Log.i("IntentService","from onHandleIntent()");
//        handler = new Handler();
//        runnable = new Runnable() {
//            @Override
//            public void run() {
//
//                new CronAsyncTask().execute();
//
//            }
//        };
//
//        handler.postDelayed(runnable,15000);
//
//    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("Service","from service");
        return null;
    }




    @Override
    public void onCreate() {
//        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
//        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
//                "MyWakelockTag");
//        wakeLock.acquire();
        super.onCreate();
        Log.i("Service","from service..onCreate()");
    }
//
    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {





        Log.i("Service","from service..onStartCommand()");

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Log.i("Service","from runnable...about to call Async");
                new CronAsyncTask().execute();

            }
        };
        handler.postDelayed(runnable,15000);
        return super.onStartCommand(intent, flags, startId);
    }
//
//    @Override
//    public void onDestroy() {
//        //wakeLock.release();
//        //CronReceiver.completeWakefulIntent(intentWork);
//        //Log.i("Service","from service..released wake lock");
//        super.onDestroy();
//    }

    class CronAsyncTask extends AsyncTask<Void,Void,Void> {
        String response;
        @Override
        protected Void doInBackground(Void... voids) {
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("tag","cron"));
            response = Utility.getDataFromTheUrl(nameValuePairs,Utility.app_url);
            if(response != null){
                Log.i("Server Response",response);
            }else{
                Log.i("Server Response","null");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //super.onPostExecute(aVoid);
            handler.postDelayed(runnable,15000);
        }
    }

}

