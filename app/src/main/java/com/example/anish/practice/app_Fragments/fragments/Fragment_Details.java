package com.example.anish.practice.app_Fragments.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.anish.practice.R;
import com.example.anish.practice.app_Fragments.interfaces.ClickInterface;


public class Fragment_Details extends Fragment implements  View.OnClickListener{
    String TAG = "Fragment_Details";
    ImageView close;
    ClickInterface clickInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"from onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG,"from onCreateView()");
        View view = inflater.inflate(R.layout.fragment__details,container,false);
        close = (ImageView)view.findViewById(R.id.close);
        close.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

        clickInterface.detailsClosed();

    }

    public void setClickInterface(ClickInterface clickInterface){

        this.clickInterface = clickInterface;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"from onDestroy()");
    }
}
