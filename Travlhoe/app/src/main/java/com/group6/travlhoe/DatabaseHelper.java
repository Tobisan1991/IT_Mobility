package com.group6.travlhoe;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by STzavelas on 28.06.17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CreateProject.db";
    public static final String TABLE_NAME = "create_project";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "PROJEKT";
    public static final String COL_3 = "DATE_FROM";
    public static final String COL_4 = "DATE_TO";
    public static final String COL_5 = "NACHNAME";
    public static final String COL_6 = "VORNAME";
    public static final String COL_7 = "KOSTENSTELLE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, PROJEKT TEXT, DATE_FROM TEXT, DATE_TO TEXT, NACHNAME TEXT, VORNAME TEXT, KOSTENSTELLE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String project, String date_from, String date_to, String name, String vorname, String kostenstelle){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, project);
        contentValues.put(COL_3, date_from);
        contentValues.put(COL_4, date_to);
        contentValues.put(COL_5, name);
        contentValues.put(COL_6, vorname);
        contentValues.put(COL_7, kostenstelle);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

}
