package com.example.anish.practice.app_BroadcastReceivers.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Anish on 11-11-2017.
 */

public class Receiver1 extends BroadcastReceiver {
    String TAG = "Receiver1";
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
}
