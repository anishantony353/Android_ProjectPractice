package com.example.anish.practice.app_Loaders;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anish.practice.R;
import com.example.anish.practice.app_Loaders.loaders.CustomLoader;

public class AsyncTaskLoader_MainActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<String> {

    String TAG = "AsyncTaskLoader_MainActivity";

    Button BT_init,BT_restart,BT_destroy;
    TextView TV_ASL;

    LoaderManager loaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_task_loader__main);
        initializeViews();
        loaderManager = getLoaderManager();

    }

    private void initializeViews() {

        BT_init = findViewById(R.id.BT_initLoader_ASL);
        BT_restart = findViewById(R.id.BT_restartLoader_ASL);
        BT_destroy = findViewById(R.id.BT_destroyLoader_ASL);
        TV_ASL = findViewById(R.id.TV_ASL);

        BT_init.setOnClickListener(this);
        BT_restart.setOnClickListener(this);
        BT_destroy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.BT_initLoader_ASL:

                loaderManager.initLoader(0,null,this);

                break;

            case R.id.BT_restartLoader_ASL:

                loaderManager.restartLoader(0,null,this);
                break;

            case R.id.BT_destroyLoader_ASL:

                loaderManager.destroyLoader(0);
                break;

        }
    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        Log.i(TAG,"onCreateLoader()");
        return new CustomLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String str) {
        Log.i(TAG,"onLoadFinished()");
        TV_ASL.setText(str);

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.i(TAG,"onLoaderReset()");
        TV_ASL.setText("");

    }
}
