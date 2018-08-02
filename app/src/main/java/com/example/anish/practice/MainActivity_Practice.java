package com.example.anish.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.anish.practice.app_ContentProviders.Main_ContentProviders;
import com.example.anish.practice.app_DynamicViews.MainActivity_DynamicViews;
import com.example.anish.practice.app_Fragments.MainActivity_NavigationDrawer;
import com.example.anish.practice.app_Fragments.MainActivity_ViewPager;
import com.example.anish.practice.app_Fragments.Transaction;
import com.example.anish.practice.app_Loaders.AsyncTaskLoader_MainActivity;
import com.example.anish.practice.app_Resources.Main_9Patch;
import com.example.anish.practice.app_Retrofit.MainActivity_Retrofit;
import com.example.anish.practice.app_Services.Main_ForegroundService;
import com.example.anish.practice.app_Voip.WalkieTalkieActivity;
import com.example.anish.practice.app_payUmoney.MainActivity_payUmoney;

public class MainActivity_Practice extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView LV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main__practice);

        LV = findViewById(R.id.LV_main_practice);
        String[] modules = getResources().getStringArray(R.array.modules);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,modules);
        LV.setAdapter(arrayAdapter);
        LV.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent;
        switch(i){
            case 0:
                intent = new Intent(this, MainActivity_payUmoney.class);
                startActivity(intent);
                break;

            case 1:
                intent = new Intent(this, Transaction.class);
                startActivity(intent);
                break;

            case 2:
                intent = new Intent(this, MainActivity_ViewPager.class);
                startActivity(intent);
                break;

            case 3:
                intent = new Intent(this, MainActivity_NavigationDrawer.class);
                startActivity(intent);
                break;



            case 4:
                intent = new Intent(this, Main_9Patch.class);
                startActivity(intent);
                break;

            case 5:
                intent = new Intent(this, MainActivity_Retrofit.class);
                startActivity(intent);
                break;

            case 6:
                intent = new Intent(this, Main_ForegroundService.class);
                startActivity(intent);
                break;

            case 7:
                intent = new Intent(this, WalkieTalkieActivity.class);
                startActivity(intent);
                break;

            case 8:
                intent = new Intent(this, Main_ContentProviders.class);
                startActivity(intent);
                break;

            case 9:
                intent = new Intent(this, AsyncTaskLoader_MainActivity.class);
                startActivity(intent);
                break;

            case 10:
                intent = new Intent(this, MainActivity_DynamicViews.class);
                startActivity(intent);
                break;



        }


    }
}
