package com.example.anish.practice.app_FileStorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.anish.practice.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GetDataFromInternalStorage extends AppCompatActivity {
    TextView name,pass;
    String CLASS_TAG = "GetDataFromInternal";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_data_from_internal_storage);
        name = (TextView)findViewById(R.id.TV_GetDataFromInternalStorage_name);
        pass = (TextView)findViewById(R.id.TV_GetDataFromInternalStorage_pass);

        try {
            int read = -1;
            StringBuffer buffer = new StringBuffer();
            FileInputStream fis = openFileInput("name_pass.txt");
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            Log.d(CLASS_TAG,"File data:"+buffer.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
