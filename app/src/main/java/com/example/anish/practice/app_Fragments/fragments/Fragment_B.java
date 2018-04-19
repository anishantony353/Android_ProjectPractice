package com.example.anish.practice.app_Fragments.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.anish.practice.R;

/**
 * Created by Anish on 14-08-2017.
 */

public class Fragment_B extends Fragment{
    TextView tv;
    String msg;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_b,container,false);
        tv = (TextView)view.findViewById(R.id.TV_fragmentB);
        if(savedInstanceState != null){
            msg = savedInstanceState.getString("msg");
            tv.setText(msg);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //tv = (TextView)getActivity().findViewById(R.id.TV_fragmentB);
    }

    public void setMsg(int index){
        this.msg = msg;
        ArrayAdapter descArray = ArrayAdapter.createFromResource(getActivity(),R.array.desc,android.R.layout.simple_list_item_1);
        String desc = descArray.getItem(index).toString();
        tv.setText(descArray.getItem(index).toString());

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("msg",msg);
    }
}
