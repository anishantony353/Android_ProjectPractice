package com.example.anish.practice.app_Threads.threads;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Created by Anish on 21-08-2017.
 */

public class Worker extends Thread {
    Handler handlerMain; //main thread Handler
    public Handler handlerWorker;
    public Worker(Handler handler){
        handlerMain = handler;
        //Log.i("Worker","");
    }
    @Override
    public void run() {
        //super.run();
        Log.i("Worker","Thread Name:"+Thread.currentThread().getId());
        Looper.prepare();

        //handler = new Handler();//worker thread Handler
        handlerWorker = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                Bundle b = message.getData();
                Log.i("Handler Worker",b.getString("msg"));
                Log.i("Handler Worker","Worker Thread Name:"+Thread.currentThread().getId());
                return true;
            }
        });

        //Looper.loop();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message msg = Message.obtain();

        Bundle b = new Bundle();
        b.putString("msg","Message from Worker");
        msg.setData(b);
        handlerMain.sendMessage(msg);
        Looper.loop();

    }
}
