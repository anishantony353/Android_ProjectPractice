package com.example.anish.practice.app_Services;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.anish.practice.R;

import aidlservice.anish.com.aidl_service.I_RemoteService;

public class MainActivity_AIDL extends AppCompatActivity {

    TextView TV_msg;
    boolean isBound;
    ServiceConnection serviceConnection;
    I_RemoteService i_remoteService;
    String TAG = "MainActivity_AIDL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__aidl);
        TV_msg = (TextView)findViewById(R.id.TV_remoteMsg);
        isBound = false;
        setServiceConnection();
    }

    private void setServiceConnection() {
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.i(TAG,"Bounded To Remote Service");
                i_remoteService = I_RemoteService.Stub.asInterface((IBinder) iBinder);
                isBound = true;
                TV_msg.setText("Bounded To Remote Service");
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.i(TAG,"Unbounded from Remote Service");
                isBound = false;
                TV_msg.setText("Unbounded from Remote Service");
            }
        };
    }

    public void bindToRemoteService(View view) {
        Intent intent = new Intent();
        intent.setClassName("aidlservice.anish.com","aidlservice.anish.com.aidl_service.services.RemoteService");
        if(isBound == false){
            bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
        }else{
            TV_msg.setText("Already Bounded");
        }
    }

    public void getMsgFromRemoteService(View view) {
        if(isBound){
            try {
                TV_msg.setText(i_remoteService.msg());
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.i(TAG,"Remote Exception");

            }
        }else{
            TV_msg.setText("Not Bounded for Text");
        }

    }

    public void unbindFromRemoteService(View view) {
        unbindService(serviceConnection);
        isBound = false;

    }
}
