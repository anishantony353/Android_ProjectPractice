package com.example.anish.practice.app_Services;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.anish.practice.R;

public class MainActivity_Messenger extends AppCompatActivity {
    TextView TV_remoteMsgMessenger;
    ServiceConnection serviceConnection;
    boolean isBounded = false;
    Messenger serviceMessenger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__messenger);
        TV_remoteMsgMessenger = (TextView)findViewById(R.id.TV_remoteMsgMessenger);
        setServiceConnection();

    }

    private void setServiceConnection() {
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                serviceMessenger = new Messenger(iBinder);
                isBounded = true;
                TV_remoteMsgMessenger.setText("Bounded To Remote Service..Messenger");

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                serviceMessenger = null;
                isBounded = false;
                TV_remoteMsgMessenger.setText("UnBounded From Remote Service..Messenger");
            }
        };
    }

    public void bindToRemoteService(View view) {
        Intent intent = new Intent();
        intent.setClassName("aidlservice.anish.com",
                "aidlservice.anish.com.aidl_service.services.RemoteServiceWithMessenger");
        if(isBounded == false){
            bindService(intent,serviceConnection,BIND_AUTO_CREATE);
        }else{
            TV_remoteMsgMessenger.setText("Already Bounded to Remote Service..Messenger");
        }
    }

    public void getMsgFromRemoteService(View view) {
        if(isBounded){
            Message reqMessage = Message.obtain();
            reqMessage.replyTo = new Messenger(new ClientHandler());
            try {
                serviceMessenger.send(reqMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else{
            TV_remoteMsgMessenger.setText("UnBounded to get msg..Messenger");
        }

    }

    public void unbindFromRemoteService(View view) {
        if(isBounded == true){
            unbindService(serviceConnection);
        }else{
            TV_remoteMsgMessenger.setText("Already UnBounded from Remote Service..Messenger");
        }

        isBounded = false;
    }

    class ClientHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Bundle responseBundle = msg.getData();
            TV_remoteMsgMessenger.setText(responseBundle.getString("msg"));
            super.handleMessage(msg);
        }
    }
}
