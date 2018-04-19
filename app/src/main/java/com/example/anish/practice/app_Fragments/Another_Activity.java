package com.example.anish.practice.app_Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.anish.practice.R;
import com.example.anish.practice.app_Fragments.fragments.Fragment_B;

public class Another_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);

        Fragment_B fragB = (Fragment_B) getFragmentManager().findFragmentById(R.id.fragmentB);
        fragB.setMsg(index);
    }
}
