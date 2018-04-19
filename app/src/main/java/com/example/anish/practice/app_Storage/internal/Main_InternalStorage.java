package com.example.anish.practice.app_Storage.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.anish.practice.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main_InternalStorage extends AppCompatActivity {
    EditText data;
    TextView dataLoc;
    TextView showData;
    FileOutputStream fos;
    FileInputStream fis;
    File internalDir;
    StringBuffer buffer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__internal_storage);
        data = (EditText) findViewById(R.id.ET_data);
        dataLoc = (TextView) findViewById(R.id.TV_dataLoc);
        showData = (TextView)findViewById(R.id.TV_showdata);
        internalDir = getFilesDir();


    }

    public void saveToInternal(View view) {
        String dataStr = data.getText().toString().trim()+"\n";
        try {
            fos = openFileOutput("data.txt", Context.MODE_APPEND);
            fos.write(dataStr.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dataLoc.setText("Saved To :"+internalDir+"/data.txt");

    }


    public void showinternalData(View view) {
        try {
            int read = -1;
            buffer = new StringBuffer();
            fis = openFileInput("data.txt");
            while((read = fis.read())!= -1){
                buffer.append((char)read);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        showData.setText(buffer);

    }
}
