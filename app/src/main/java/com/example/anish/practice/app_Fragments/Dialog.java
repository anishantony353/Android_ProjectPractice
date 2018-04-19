package com.example.anish.practice.app_Fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.anish.practice.R;
import com.example.anish.practice.app_Fragments.fragments.FragmentDialog;

import java.io.Serializable;

public class Dialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);


    }
    public void showDialog(View view){
        FragmentManager manager = getFragmentManager();
        FragmentDialog dialog = new FragmentDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj",new ArgClass());

        dialog.setArguments(bundle);

        dialog.show(manager,"dialog");
        android.app.Dialog d = dialog.getDialogInActivity();

        if(d != null){
            Log.i("Dialog","getDialog() != NULL");
            d.setTitle("Dialog title from Activity");
        }else{
            Log.i("Dialog","getDialog() == NULL");
        }
    }
    public void showDialogOnActivy(View view){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        FragmentDialog fragDialog = new FragmentDialog();
        transaction.add(R.id.Container_dialogFragment,fragDialog);
        transaction.commit();
    }
    public class ArgClass implements Serializable{
        public String str = "abcdef";

        public void getValue(){

        }

    }
}
