package com.example.anish.practice.app_MemoryLeak;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.anish.practice.R;

public class MainActivity_Leak extends AppCompatActivity {
    //RandomClass randomClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__leak);

        //randomClass = RandomClass.getRandomClass(this);
        //randomClass = new RandomClass(this);

    }


}
