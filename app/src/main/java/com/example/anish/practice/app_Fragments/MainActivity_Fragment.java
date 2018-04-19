package com.example.anish.practice.app_Fragments;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.anish.practice.R;
import com.example.anish.practice.app_Fragments.fragments.Fragment_A;
import com.example.anish.practice.app_Fragments.fragments.Fragment_B;

public class MainActivity_Fragment extends AppCompatActivity implements Fragment_A.CommunicateFragments{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__fragment);
        Fragment_A fragment_a = (Fragment_A) getFragmentManager().findFragmentById(R.id.fragmentA);
        fragment_a.setCommunicator(this);
    }

    @Override
    public void clicked(int index) {
        FragmentManager fmgr = getFragmentManager();
        Fragment_B fragment_b = (Fragment_B) fmgr.findFragmentById(R.id.fragmentB);

        if(fragment_b != null && fragment_b.isVisible()){
            fragment_b.setMsg(index);
        }else{
            Intent intent = new Intent(this,Another_Activity.class);
            intent.putExtra("index",index);
            startActivity(intent);
        }

    }
}
