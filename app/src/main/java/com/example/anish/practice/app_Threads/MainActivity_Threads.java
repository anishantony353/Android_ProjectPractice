package com.example.anish.practice.app_Threads;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.anish.practice.R;
import com.example.anish.practice.app_Threads.threads.Worker;


public class MainActivity_Threads extends AppCompatActivity {
    static Handler handler; //main thread Handler
    Worker worker;   //different Thread
    TextView tv_ui_thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__threads);
        tv_ui_thread = (TextView)findViewById(R.id.tv_ui_thread);
        Log.i("Main","Thread Name:"+Thread.currentThread().getId());
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                tv_ui_thread.setText("Message from Worker Thread");
                /*
                Bundle b = message.getData();
                Log.i("Handler Main",b.getString("msg"));
                Log.i("Handler Main","Main Thread Name:"+Thread.currentThread().getId());

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message msg = Message.obtain();
                Bundle bFromMain = new Bundle();
                bFromMain.putString("msg","Message from Main");
                msg.setData(bFromMain);
                worker.handlerWorker.sendMessage(msg);
                /*
                worker.handlerWorker.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("Worker","Worker Thread Name..Executed from Main:"+Thread.currentThread().getId());
                    }
                });*/

                return true;
            }
        });

        //worker = new Worker(handler);
        //worker.start();

        new Thread(new Task()).start();

    }

    public void nextActivity(View view) {
        startActivity(new Intent(this,NextActivity.class));
        finish();
    }

    class Task implements Runnable{

        @Override
        public void run() {
            Log.i("WalkieTalkieActivity","Thread Name:"+Thread.currentThread().getId());
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("WalkieTalkieActivity","About to send message");
            handler.sendEmptyMessage(1);
            //Toast.makeText(getApplicationContext(),"Message",Toast.LENGTH_LONG);
        }
    }
}
