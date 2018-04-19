package com.example.anish.practice.app_Services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.anish.practice.R;
import com.example.anish.practice.app_Services.utilities.Constants;
import com.example.anish.practice.app_Services.services.ForegroundService;

public class Main_ForegroundService extends AppCompatActivity implements View.OnClickListener {
    Button BT_startForeground,BT_stopForeground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services__foreground);

        BT_startForeground = findViewById(R.id.BT_startForeground);
        BT_startForeground.setOnClickListener(this);
        BT_stopForeground = findViewById(R.id.BT_stopForeground);
        BT_stopForeground.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.BT_startForeground:

                Intent startIntent = new Intent(this, ForegroundService.class);
                startIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
                startService(startIntent);

                break;

            case R.id.BT_stopForeground:

                Intent stopIntent = new Intent(this, ForegroundService.class);
                stopIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
                startService(stopIntent);

                break;



        }
    }


}
