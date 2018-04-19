package com.example.anish.practice.threads;


import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.example.anish.practice.utility.Utility;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anish on 07-07-2017.
 */

public class CronThread extends Thread {
    Handler handler;
    Runnable runnable;

    @Override
    public void run() {
        Log.i("Thread","from Thread");
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                //Log.i("Thread","from Runnable after 15 secs");
                new CronAsyncTask().execute();

            }
        };
        handler.postDelayed(runnable,15000);

        //super.run();
    }
    class CronAsyncTask extends AsyncTask<Void,Void,Void>{
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
