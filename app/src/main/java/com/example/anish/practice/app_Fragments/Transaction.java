package com.example.anish.practice.app_Fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.anish.practice.R;
import com.example.anish.practice.app_Fragments.fragments.Fragment_C;
import com.example.anish.practice.app_Fragments.fragments.Fragment_D;

public class Transaction extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{
    FragmentManager manager = getFragmentManager();

    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        manager.addOnBackStackChangedListener(this);
    }
    public void addC(View view){
        transaction = manager.beginTransaction();
//        if(manager.findFragmentByTag("C") != null){
//            transaction.add(R.id.frag_container,manager.findFragmentByTag("C"));
//        }else{
            Fragment_C c = new Fragment_C();
            transaction.add(R.id.frag_container,c,"C");
            transaction.addToBackStack("Add C");
//
//        }
        //Log.i("Transaction","About to commit");
        transaction.commit();
    }
    public void addD(View view){
        transaction = manager.beginTransaction();
//        Fragment_D d = (Fragment_D) manager.findFragmentByTag("D");
//        if(d != null){
//            transaction.replace(R.id.frag_container,d);
//        }else{

             Fragment_D d = new Fragment_D();
             transaction.add(R.id.frag_container,d,"D");
             transaction.addToBackStack("Add D");
//            transaction.replace(R.id.frag_container,d,"D");
//
//        }
        //Log.i("Transaction","About to commit");
        transaction.commit();

    }
    public void detachD(View view){
        transaction = manager.beginTransaction();
        if(manager.findFragmentByTag("D") != null){
            transaction.detach(manager.findFragmentByTag("D"));
            transaction.addToBackStack("Detach D");
           // Log.i("Transaction","About to commit");
            transaction.commit();
        }else{
            Toast.makeText(this,"No D to detach",Toast.LENGTH_LONG);

        }

    }
    public void attachC(View view){
        transaction = manager.beginTransaction();
        if(manager.findFragmentByTag("C") != null){
            transaction.attach(manager.findFragmentByTag("C"));
            transaction.addToBackStack("Attach C");
            //Log.i("Transaction","About to commit");
            transaction.commit();
        }else{
            Toast.makeText(this,"No C to Attach",Toast.LENGTH_LONG);
        }
    }
    public void detachC(View view){
        transaction = manager.beginTransaction();
        if(manager.findFragmentByTag("C") != null){
            transaction.detach(manager.findFragmentByTag("C"));
            transaction.addToBackStack("Detach C");
            //Log.i("Transaction","About to commit");
            transaction.commit();
        }else{
            Toast.makeText(this,"No C to Detach",Toast.LENGTH_LONG);

        }
    }
    public void pop(View view){
        manager.popBackStack();



    }

    @Override
    public void onBackStackChanged() {
        int backStackCount = manager.getBackStackEntryCount();
        for(int i=(backStackCount-1);i>=0;i--){
            Log.i("BackStack Pos",i+" : "+manager.getBackStackEntryAt(i).getName());
        }
    }
}
