package com.example.anish.practice.app_Loaders;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.anish.practice.R;

public class CursorLoader_MainActivity extends AppCompatActivity implements View.OnClickListener,LoaderManager.LoaderCallbacks<Cursor>{

    String TAG = "CursorLoader_MainActivity";
    Button BT_create,BT_restart,BT_destroy;
    ListView LV_contacts;

    LoaderManager loaderManager;

    SimpleCursorAdapter mAdapter;


    static final String[] CONTACTS_SUMMARY_PROJECTION = new String[] {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.CONTACT_STATUS,
            ContactsContract.Contacts.CONTACT_PRESENCE,
            ContactsContract.Contacts.PHOTO_ID,
            ContactsContract.Contacts.LOOKUP_KEY,
    };

    boolean dataLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loader__main_activity);
        initializeViews();

        loaderManager = getLoaderManager();

        mAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, null,
                new String[] { ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.CONTACT_STATUS },
                new int[] { android.R.id.text1, android.R.id.text2 }, 0);

        LV_contacts.setAdapter(mAdapter);

        if(savedInstanceState != null){
            Log.i(TAG,"savedInstanceState not NULL");
            Log.i(TAG,"savedInstanceState dataLoaded:"+savedInstanceState.getBoolean("dataLoaded"));

            if(savedInstanceState.getBoolean("dataLoaded")){
                Log.i(TAG,"savedInstanceState dataLoaded = True");
                loaderManager.restartLoader(0,null,this);
            }
        }

    }

    private void initializeViews() {
        BT_create = findViewById(R.id.BT_initLoader);
        BT_restart = findViewById(R.id.BT_restartLoader);
        BT_destroy = findViewById(R.id.BT_destroyLoader);

        BT_create.setOnClickListener(this);
        BT_restart.setOnClickListener(this);
        BT_destroy.setOnClickListener(this);

        LV_contacts = findViewById(R.id.LV_contacts);
    }

    @Override
    public CursorLoader onCreateLoader(int i, Bundle bundle) {

        Toast.makeText(this,"onCreateLoader()",Toast.LENGTH_SHORT);

        Log.i(TAG,"onCreateLoader()");

        Uri baseUri;

            baseUri = ContactsContract.Contacts.CONTENT_URI;



        String select = "((" + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND ("
                + ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1) AND ("
                + ContactsContract.Contacts.DISPLAY_NAME + " != '' ))";
        return new CursorLoader(this, baseUri,
                CONTACTS_SUMMARY_PROJECTION, select, null,
                ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor cursor) {

        Toast.makeText(this,"onLoadFinished()",Toast.LENGTH_SHORT);

        Log.i(TAG,"onLoadFinished()");
        if(cursor != null){
            Log.i(TAG,"onLoadFinished()..Cursor Not NULL..."+dataLoaded);
            dataLoaded = true;
            Log.i(TAG,"onLoadFinished()..Cursor Not NULL...."+dataLoaded);
        }

        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader loader) {

        Toast.makeText(this,"onLoaderReset()",Toast.LENGTH_SHORT);

        Log.i(TAG,"onLoaderReset()");
        mAdapter.swapCursor(null);
        dataLoaded = false;

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.BT_initLoader:

                loaderManager.initLoader(0,null,this);
                break;

            case R.id.BT_restartLoader:

                loaderManager.restartLoader(0,null,this);
                break;

            case R.id.BT_destroyLoader:

                loaderManager.destroyLoader(0);
                break;

        }

    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i(TAG,"onSaveInstanceState() dataLoaded = "+dataLoaded);
        outState.putBoolean("dataLoaded",dataLoaded);
        super.onSaveInstanceState(outState);
    }
}
