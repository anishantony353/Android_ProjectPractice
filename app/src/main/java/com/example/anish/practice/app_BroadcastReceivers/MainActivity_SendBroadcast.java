package com.example.anish.practice.app_BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.anish.practice.R;

public class MainActivity_SendBroadcast extends AppCompatActivity {

    String TAG = "MainActivity_SendBroadcast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__send_broadcast);
    }

    public void sendBroadcast(View view) {
        Intent intent = new Intent();
        intent.setAction("com.example.anish.practice.app_BroadcastReceivers.receivers.Receiver");
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        sendOrderedBroadcast(intent, null, new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle
                        = getResultExtras(true);
                String content = bundle.getString("str");
                if(content == null){
                    Log.i(TAG,"Start -> "+TAG);
                    bundle.putString("str","Start -> "+TAG);
                }else{
                    Log.i(TAG,content+"->"+TAG);
                    bundle.putString("str",content+"->"+TAG);
                }

            }
        },null,MainActivity_SendBroadcast.RESULT_OK,null,null);
    }
}
