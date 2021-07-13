package com.example.lab3kulbaka;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Kulbaka Nataly
 * @date 06.05.2021
 */
public class ClassmatesDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "app.db";

    public ClassmatesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS classmates (" +
                           "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                           "name TEXT, " +
                           "created_at DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table classmates");
        db.execSQL("CREATE TABLE classmates (" +
                           "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                           "first_name TEXT, " +
                           "second_name TEXT, " +
                           "last_name TEXT, " +
                           "created_at DATETIME DEFAULT CURRENT_TIMESTAMP)");
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}