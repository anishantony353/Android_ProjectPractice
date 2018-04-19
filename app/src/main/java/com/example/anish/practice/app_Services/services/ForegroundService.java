package com.example.anish.practice.app_Services.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.anish.practice.R;
import com.example.anish.practice.app_Services.Main_ForegroundService;
import com.example.anish.practice.app_Services.utilities.Constants;

/**
 * Created by Anish on 07-02-2018.
 */

public class ForegroundService extends Service{

    String TAG = "ForegroundService";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "i from Service");
        Log.d(TAG, "d from Service");
        Log.e(TAG, "e from Service");
        Log.v(TAG, "v from Service");
        Log.w(TAG, "w from Service");

        switch(intent.getAction()){
            case Constants.ACTION.STARTFOREGROUND_ACTION:

                Log.i(TAG, "Received Start Foreground Intent ");
                Intent notificationIntent = new Intent(this, Main_ForegroundService.class);
                notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
                notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                        notificationIntent, 0);

                Intent previousIntent = new Intent(this, ForegroundService.class);
                previousIntent.setAction(Constants.ACTION.PREV_ACTION);
                PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                        previousIntent, 0);

                Intent playIntent = new Intent(this, ForegroundService.class);
                playIntent.setAction(Constants.ACTION.PLAY_ACTION);
                PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                        playIntent, 0);

                Intent nextIntent = new Intent(this, ForegroundService.class);
                nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
                PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                        nextIntent, 0);

                Bitmap icon = BitmapFactory.decodeResource(getResources(),
                        R.drawable.dialog);

                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("Music Player")
                        .setTicker("Music Player")
                        .setContentText("My Music")
                        .setSmallIcon(R.drawable.message_icon)
                        .setContentIntent(pendingIntent)
                        .setOngoing(false)
                        .setAutoCancel(true)
                        .addAction(android.R.drawable.ic_media_previous,
                                "Previous", ppreviousIntent)
                        .addAction(android.R.drawable.ic_media_play, "Play",
                                pplayIntent)
                        .addAction(android.R.drawable.ic_media_next, "Next",
                                pnextIntent).build();
                startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,
                        notification);

                break;

            case Constants.ACTION.PREV_ACTION:
                Log.i(TAG,"Clicked Previous");

                break;

            case Constants.ACTION.PLAY_ACTION:

                Log.i(TAG,"Clicked Play");
                stopForeground(false);

                break;

            case Constants.ACTION.NEXT_ACTION:

                Log.i(TAG,"Clicked Next");

                break;

            case Constants.ACTION.STOPFOREGROUND_ACTION:
                Log.i(TAG,"Stopping ForegroundService and Service");
                stopForeground(true);
                stopSelf();

                break;


        }
        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.i(TAG,"onTaskRemoved()");
        /*
        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
        restartServiceIntent.setPackage(getPackageName());

        PendingIntent restartServicePendingIntent = PendingIntent.getService(getApplicationContext(), 1, restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmService = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmService.set(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 1000,
                restartServicePendingIntent);
        */

        super.onTaskRemoved(rootIntent);
    }


}
