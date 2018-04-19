package com.example.anish.practice.app_Loaders.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

/**
 * Created by Anish on 16-04-2018.
 */

public class CustomLoader extends AsyncTaskLoader<String> {
    String TAG = "CustomLoader";
    int count = 0;

    public CustomLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        Log.i(TAG,"onStartLoading()");
        forceLoad();
    }

    @Override
    public String loadInBackground() {

        Log.i(TAG,"loadInBackground()");

        try {
            Thread.sleep(4000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String msg = "Message from Loader:"+ ++count;

        Log.i(TAG,"returning msg:"+msg);

        return msg;
    }

}
