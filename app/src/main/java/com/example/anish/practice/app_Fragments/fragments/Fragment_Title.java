package com.example.anish.practice.app_Fragments.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anish.practice.R;
import com.example.anish.practice.app_Fragments.interfaces.ClickInterface;


public class Fragment_Title extends Fragment implements View.OnClickListener{
    String TAG = "Fragment_Title";
    DrawerLayout drawerLayout;
    DrawerLayout.DrawerListener drawerListener;
    TextView textView;
    ClickInterface clickInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"from onCreate()");

        drawerListener = new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG,"from onCreateView()");
        View view = inflater.inflate(R.layout.fragment__title,container,false);
        textView = (TextView) view.findViewById(R.id.TV_NavDrawer);
        textView.setOnClickListener(this);
        return view;
    }



    @Override
    public void onClick(View view) {
        Log.i(TAG,"TV Clicked");
        clickInterface.titleClicked();

    }

    public void setClickInterface(ClickInterface clickInterface){
        this.clickInterface = clickInterface;

    }




}
