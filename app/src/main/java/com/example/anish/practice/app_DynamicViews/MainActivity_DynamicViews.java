package com.example.anish.practice.app_DynamicViews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.anish.practice.R;
import com.example.anish.practice.utility.Utility;

public class MainActivity_DynamicViews extends AppCompatActivity {
    private String TAG = "MainActivity_DynamicViews";
    LinearLayout LL_root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__dynamic_views);
        LL_root = findViewById(R.id.LL_root);
    }

    public void addView(View view){
        Utility.log(TAG,"from addView()");
        Button myButton = new Button(this);
        //myButton.setText("Btn");
        //myButton.setPadding();
        myButton.setLayoutParams(new LinearLayout.LayoutParams(
               60,
                30));

        LL_root.addView(myButton);
    }
}
