package com.example.anish.practice.app_ContentProvider;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.anish.practice.R;


public class MainActivity_Contacts extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    String TAG = "MainActivity_Contacts";
    boolean isSet = false;
    //String[] projectionContacts={ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER};
    String[] columnsSimpleCursorAdapter={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER};
    String[] projectionContacts={ContactsContract.CommonDataKinds.Phone._ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER};
    String orderBy = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
    ListView listView;
    int[] lv_row_views = {R.id.lv_row_name,R.id.lv_row_number};

    SimpleCursorAdapter cursorAdapter;

    boolean isLoaded = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"from onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__contacts);
        listView = (ListView)findViewById(R.id.LV_contacts);
        setListView();
        getLoaderManager();
        //projectionContacts = {ContactsContract.Contacts.DISPLAY_NAME};

    }

    private void setListView() {
        cursorAdapter = new SimpleCursorAdapter(getApplicationContext(),
                R.layout.lv_row_simple_curser_adapter,
                null,
                columnsSimpleCursorAdapter,
                lv_row_views);
        listView.setAdapter(cursorAdapter);
    }

    public void getContacts(View view) {
        if(isSet == false){
            Log.i(TAG,"about to Init loader");
            getLoaderManager().initLoader(0,null,this);
        }else{
            Log.i(TAG,"about to Restart loader");
            getLoaderManager().restartLoader(0,null,this);
        }

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Log.i(TAG,"from onCreateLoader()");
        isSet = true;
        return new CursorLoader(this, ContactsContract.CommonDataKinds.Phone.CONTENT_URI,projectionContacts,null,null,orderBy);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.i(TAG,"from onLoadFinished()");
        if(cursor != null &&cursor.getCount() > 0){
            Log.i(TAG,"Cursor size:"+cursor.getCount());
//            while(cursor.moveToNext()){
//                Log.i(TAG,cursor.getString(0)+"\n"+cursor.getString(1));
//            }

            cursorAdapter.swapCursor(cursor);
            isLoaded = true;
        }else{
            Log.i(TAG,"Failed to Fetch data");
        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.i(TAG,"from onLoaderReset()");
        cursorAdapter.swapCursor(null);
    }

    @Override
    protected void onPause() {
        Log.i(TAG,"from onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG,"from onStop()");
        super.onStop();
    }

    @Override
    protected void onResume() {
        Log.i(TAG,"from onResume()");
        super.onResume();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i(TAG,"from onSaveInstanceState()");
        super.onSaveInstanceState(outState);
        outState.putBoolean("isLoaded",isLoaded);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i(TAG,"from onRestoreInstanceState()");
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState.getBoolean("isLoaded",false)){
            Log.i(TAG,"about to Init loader");
            getLoaderManager().initLoader(0,null,this);
        }

    }

    //    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//        Log.i(TAG,"from onLoaderReset()");
//
//    }
}
