package com.example.anish.practice.app_SqLite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Anish on 15-11-2017.
 */

public class DB_handler extends SQLiteOpenHelper{
    public static String db_name = "test_db";
    public static int db_version = 1;

    public static DB_handler db_handler;
    public SQLiteDatabase db;

    public DB_handler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DB_handler(Context context){
        super(context,db_name,null,db_version);

        if( db == null || (!db.isOpen()) ){
            db = this.getWritableDatabase();
        }

    }

    public static DB_handler getInstance(Context context){
        if(db_handler == null){
            db_handler = new DB_handler(context);
        }

        return db_handler;

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase getDB(){

        if( db == null || (!db.isOpen()) ){
            db = this.getWritableDatabase();
        }

        return db;
    }
}
