package com.example.anish.practice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Cron extends AppCompatActivity {
    //WakefulBroadcastReceiver receiver;
    Context context = this;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cron);
        button = (Button)findViewById(R.id.BT_nextActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,CronActivity2.class));
            }
        });

//        receiver = new WakefulBroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Log.i("Cron","from receiver");
//                startService(new Intent(context,CronService.class));
//            }
//        };
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("receiver");
//        registerReceiver(receiver,intentFilter);
        sendBroadcast(new Intent("receiver"));

        //Intent intent = new Intent(this, CronReceiver.class);
        //sendBroadcast(intent);


        //Intent service = new Intent(this, CronService.class);
        //PowerManager.WakeLock wakeLock;
        //PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        //wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                //"MyWakelockTag");
        //wakeLock.acquire();
        //service.putExtra("wl",wakeLock);
        //startService(service);

    }
}
