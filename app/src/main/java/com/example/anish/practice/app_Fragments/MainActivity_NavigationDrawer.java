package com.example.anish.practice.app_Fragments;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.anish.practice.R;
import com.example.anish.practice.app_Fragments.fragments.Fragment_Details;
import com.example.anish.practice.app_Fragments.fragments.Fragment_Title;
import com.example.anish.practice.app_Fragments.interfaces.ClickInterface;

public class MainActivity_NavigationDrawer extends AppCompatActivity implements ClickInterface{
    String TAG = "MainActivity_NavigationDrawer";
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__navigation_drawer);

        Fragment_Title fragment_title = new Fragment_Title();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.NavFrag,fragment_title).commit();
        fragment_title.setClickInterface(this);

    }


    @Override
    public void titleClicked() {
        Log.i(TAG,"Title Clicked");
        showDetails();

    }

    @Override
    public void detailsClosed() {
        Log.i(TAG,"Details Closed");
        fragmentManager.popBackStack();

    }

    public void showDetails(){

        Fragment_Details detailsFrag = new Fragment_Details();

        transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right);
        transaction.replace(R.id.NavFrag,detailsFrag,"details");

        transaction.addToBackStack("Add details");

        transaction.commit();
        detailsFrag.setClickInterface(this);
    }
}
