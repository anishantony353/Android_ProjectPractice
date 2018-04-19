package com.example.anish.practice.app_SqLite.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.anish.practice.app_SqLite.db.DB_handler;

/**
 * Created by Anish on 15-11-2017.
 */

public class Dao {
    //Context context;
    SQLiteDatabase db;
    DB_handler db_handler;

    public Dao(Context context){
        //this.context = context;

        db_handler = DB_handler.getInstance(context);
        db = db_handler.getDB();

    }
}
