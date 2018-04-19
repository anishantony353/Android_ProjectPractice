package com.example.anish.practice.app_FileStorage;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anish.practice.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalStorage extends AppCompatActivity {
    EditText ET_name,ET_pass;
    Button BT_save;
    Context context = this;
    String CLASS_TAG = "InternalStorage.java";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internal_storage);
        Log.d(CLASS_TAG,"External Storage state:"+ Environment.getExternalStorageState());

        ET_name = (EditText)findViewById(R.id.ET_InternalStorage_name);
        ET_pass = (EditText)findViewById(R.id.ET_InternalStorage_pass);
        BT_save = (Button)findViewById(R.id.BT_InternalStorage_save);
        BT_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //// Internal
                //writeDataToInternal("internal.txt",ET_name.getText().toString());
                //readDataFromInternal("internal.txt");

                //// Internal Cache
                //File cacheInternal = getCacheDir();
                //File file = new File(cacheInternal,"cache_internal.txt");


                //// External Cache

                File cacheExternal = new File(getExternalCacheDir(),"sub_cache_external");
                if(! cacheExternal.exists()){
                    cacheExternal.mkdirs();

                }
                File file = new File(cacheExternal,"cache_external.txt");


                //// External Private
                //File externalPrivate = getExternalFilesDir("anish_storage_demo");
                //File file = new File(externalPrivate,"external_private.txt");

                //// External public
//                File externalPublic = Environment.getExternalStoragePublicDirectory("StorageApp/sub");
//                if(!externalPublic.exists()){
//                    externalPublic.mkdirs();
//
//                }
//                File file = new File(externalPublic,"external_public.txt");

                writeData(file,ET_name.getText().toString());
                readData(file);


            }
        });

    }


    private void writeData(File file,String content) {
        FileOutputStream fos = null;
        //File cacheDir = getCacheDir();
        //File file = new File(cacheDir,fileName);
        try {
            fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            Log.d(CLASS_TAG,"Saved to "+file.getPath());
            Toast.makeText(context,"Saved to "+file.getPath(),Toast.LENGTH_LONG).show();
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
    }
    private void readData(File file){
        FileInputStream fis = null;
        //File cacheDir = getCacheDir();
        //File file = new File(cacheDir,fileName);

        try {
            fis = new FileInputStream(file);
            int read = -1;
            StringBuffer buffer = new StringBuffer();
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

    private void writeDataToInternal(String fileName,String content){

        FileOutputStream fos = null;
        try {

            fos = openFileOutput(fileName,MODE_PRIVATE);
            fos.write(content.getBytes());
            Log.d(CLASS_TAG,"Saved to"+getFilesDir().toString()+"/"+fileName);
            Toast.makeText(context,"Saved to"+getFilesDir().toString()+"/"+fileName,Toast.LENGTH_LONG).show();
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
    }
    private void readDataFromInternal(String fileName) {
        FileInputStream fis = null;
        try {
            int read = -1;
            StringBuffer buffer = new StringBuffer();
            fis = openFileInput(fileName);
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            Log.d(CLASS_TAG,"File data:"+buffer.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
