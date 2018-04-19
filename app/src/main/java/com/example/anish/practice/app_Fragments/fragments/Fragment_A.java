package com.example.anish.practice.app_Fragments.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.anish.practice.R;

/**
 * Created by Anish on 14-08-2017.
 */

public class Fragment_A extends Fragment implements AdapterView.OnItemClickListener {
    Button bt;
    ListView lv;
    CommunicateFragments com;
    int counter = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState != null){
            counter = savedInstanceState.getInt("counter",0);
        }
        View view = inflater.inflate(R.layout.fragment_a,container,false);
        //bt = (Button)view.findViewById(R.id.BT_fragmentA);
        //bt.setOnClickListener(this);
        lv = (ListView) view.findViewById(R.id.LV_fragA);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.title,android.R.layout.simple_list_item_1);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //com = (CommunicateFragments) getActivity(); //making com as MainActivity
//        bt = (Button)getActivity().findViewById(R.id.BT_fragmentA);
//        bt.setOnClickListener(this);

    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter",counter);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        com.clicked(i);
    }

    public interface CommunicateFragments {
        public void clicked(int index);
    }
    public void setCommunicator(CommunicateFragments com){
        this.com = com;
    }
}
