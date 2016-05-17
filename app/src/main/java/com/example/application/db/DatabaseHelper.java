package com.example.application.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kerry on 13/05/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Notie.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA = ",";
    private static final String SQL_CREATE_NOTES =
            "CREATE TABLE " + Contract.Notes.TABLE_NAME + " (" +
                    Contract.Notes._ID + " INTEGER PRIMARY KEY, " +
                    Contract.Notes.COLUMN_TITLE + TEXT_TYPE + COMMA +
                    Contract.Notes.COLUMN_DESCRIPTION + TEXT_TYPE + ")";

    /*
    * CREATE TABLE notes (
    *   _ID INTEGER PRIMARY KEY,
    *   title TEXT,
    *   description TEXT)
    * */

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
