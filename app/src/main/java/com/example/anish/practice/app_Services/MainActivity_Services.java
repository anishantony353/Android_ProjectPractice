package com.example.anish.practice.app_Services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.anish.practice.R;
import com.example.anish.practice.app_Services.services.BoundedService;
import com.example.anish.practice.app_Services.services.NormalService;

public class MainActivity_Services extends AppCompatActivity {
    String TAG = "MainActivity_Services";
    TextView TV_msg;

    Intent normalService;
    Intent boundedService;
    ServiceConnection serviceConnection;
    boolean isServiceBounded = false;
    BoundedService boundService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__services);
        TV_msg = (TextView)findViewById(R.id.TV_msg);
        boundedService = new Intent(getApplicationContext(), BoundedService.class);
        Log.i(TAG,"Thread:"+Thread.currentThread().getId());
        //startNormalService();

    }

    private void startNormalService() {
        normalService = new Intent(getApplicationContext(), NormalService.class);
        normalService.putExtra("key",1);
        startService(normalService);
    }

    public void stopBoundedService(View view) {
        stopService(boundedService);
    }

    public void startBoundedService(View view) {


        startService(boundedService);
    }

    public void bindToService(View view) {


            if(serviceConnection == null){
                serviceConnection = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        BoundedService.ServiceBinder serviceBinder = (BoundedService.ServiceBinder)iBinder;
                        boundService  = serviceBinder.getService();
                        isServiceBounded = true;
                        Log.i(TAG,"Service Bounded");
                        TV_msg.setText("Service Bounded");
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName componentName) {

                        isServiceBounded = false;
                        Log.i(TAG,"Service UnBounded");
                        TV_msg.setText("Service UnBounded");
                    }
                };
            }
            bindService(boundedService,serviceConnection,Context.BIND_AUTO_CREATE);


    }

    public void unBindService(View view) {

        if(isServiceBounded){
            unbindService(serviceConnection);
            isServiceBounded = false;
        }else{
            TV_msg.setText("Service Not Bounded to Unbind");
        }

    }

    public void getServiceMsg(View view) {
        if(isServiceBounded){
            TV_msg.setText(boundService.getMessage());
        }else{
            TV_msg.setText("Service Not Bounded for Text");
        }
    }
}
