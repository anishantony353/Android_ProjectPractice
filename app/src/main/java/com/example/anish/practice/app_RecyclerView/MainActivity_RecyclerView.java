package com.example.anish.practice.app_RecyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.anish.practice.R;
import com.example.anish.practice.app_RecyclerView.adapters.MainAdapter;
import com.example.anish.practice.app_RecyclerView.ui.RV_item_decor;

public class MainActivity_RecyclerView extends AppCompatActivity {
    RecyclerView rv_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__recycler_view);
        rv_main = (RecyclerView) findViewById(R.id.RV_main);
        rv_main.setLayoutManager(new LinearLayoutManager(this));
        rv_main.setItemAnimator(new DefaultItemAnimator());

        rv_main.addItemDecoration(new RV_item_decor("main"));

        MainAdapter adapter = new MainAdapter();
        rv_main.setAdapter(adapter);
    }
}
