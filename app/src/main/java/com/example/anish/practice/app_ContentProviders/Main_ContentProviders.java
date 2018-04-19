package com.example.anish.practice.app_ContentProviders;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.anish.practice.R;

public class Main_ContentProviders extends AppCompatActivity implements View.OnClickListener {

    TextView TV_msg;
    EditText ET_rawContact;
    Button BT_submitRawContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content_providers);
        initializeViews();

        getContentResolver().delete(ContactsContract.RawContacts.CONTENT_URI,ContactsContract.RawContacts.ACCOUNT_TYPE+"= ?",
                new String[]{"com.demo"});
        getDictionaryWordsCount();
    }

    private void initializeViews() {
        TV_msg = findViewById(R.id.TV_CP_msg);
        ET_rawContact = findViewById(R.id.ET_rawContact);
        BT_submitRawContact = findViewById(R.id.BT_submitRawContact);
        BT_submitRawContact.setOnClickListener(this);
    }

    private void getDictionaryWordsCount() {

        String[] mProjectionContacts =
                {
                        ContactsContract.Contacts._ID,    // Contract class constant for the _ID column name

                };
        String[] mProjectionRawContacts =
                {
                        ContactsContract.RawContacts._ID,    // Contract class constant for the _ID column name

                };
        String[] mProjectionData =
                {
                        ContactsContract.Data._ID,    // Contract class constant for the _ID column name

                };

        //String mSelectionClause = null;

        //String[] mSelectionArgs = {""};

        Cursor cursorContacts = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                mProjectionContacts,
                null,
                null,
                null);

        Cursor cursorRawContacts = getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI,
                mProjectionRawContacts,
                null,
                null,
                null);
        Cursor cursorData = getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                mProjectionData,
                null,
                null,
                null);

        if(cursorContacts != null && cursorRawContacts != null){
            TV_msg.setText("Number of CONTACTS:"+cursorContacts.getCount()+"\n"+
                    "Number of RAW CONTACTS:"+cursorRawContacts.getCount()+"\n"+
                    "Number of DATA:"+cursorData.getCount());
        }else{
            TV_msg.setText("Cursor is NULL");
        }
    }

    @Override
    public void onClick(View view) {
        if(ET_rawContact.getText().toString().trim().length() != 0){

            ContentValues values = new ContentValues();
            values.put(ContactsContract.RawContacts.ACCOUNT_TYPE, "com.demo");
            values.put(ContactsContract.RawContacts.ACCOUNT_NAME, "demo_acc_name");
            Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
            long rawContactId = ContentUris.parseId(rawContactUri);

            values.clear();
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, ET_rawContact.getText().toString().trim());
            getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

            getDictionaryWordsCount();


        }


    }
}
