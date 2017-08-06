package com.group6.TakeOff;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by STzavelas on 28.06.17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TakeOff.db";

    //Table Names
    public static final String TABLE_PROJEKT = "create_project";
    public static final String TABLE_UNTERKUNFT = "unterkunft";
    public static final String TABLE_AUTO = "auto";
    public static final String TABLE_FLUGZEUG = "flugzeug";
    public static final String TABLE_TAXI = "taxi";
    public static final String TABLE_BAHN  = "bahn";


    //Common column names
    public static final String KEY_ID = "ID";
    public static final String KEY_PROJECT = "PROJEKT";

    //create project column names
    public static final String KEY_DATE_FROM= "DATE_FROM";
    public static final String KEY_DATE_TO= "DATE_TO";
    public static final String KEY_NAME = "NACHNAME";
    public static final String KEY_VORNAME = "VORNAME";
    public static final String KEY_KOSTENST = "KOSTENSTELLE";

    //Expenses column names
    public static final String KEY_ENTFERNUNG= "ENTFERNUNG";
    public static final String KEY_PRICE= "PRICE";
    public static final String KEY_MWST= "MWST";
    public static final String KEY_RECHNUNG_IMG= "RECHNUNG_IMG";


    //CREATE TABLE STATEMENTS
    private static final String CREATE_TABLE_PROJEKT = "create table " + TABLE_PROJEKT +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "PROJEKT TEXT, " +
            "DATE_FROM TEXT, " +
            "DATE_TO TEXT, " +
            "NACHNAME TEXT, " +
            "VORNAME TEXT, " +
            "KOSTENSTELLE TEXT)";

    private static final String CREATE_TABLE_UNTERKUNFT = "create table " + TABLE_UNTERKUNFT +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "PROJEKT TEXT, " +
            "ENTFERNUNG INTEGER, " +
            "PRICE INTEGER, " +
            "MWST INTEGER)";

    private static final String CREATE_TABLE_AUTO = "create table " + TABLE_AUTO +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "PROJEKT TEXT, " +
            "ENTFERNUNG INTEGER, " +
            "PRICE INTEGER, " +
            "MWST INTEGER)";

    private static final String CREATE_TABLE_FLUGZEUG = "create table " + TABLE_FLUGZEUG +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "PROJEKT TEXT, " +
            "ENTFERNUNG INTEGER, " +
            "PRICE INTEGER, " +
            "MWST INTEGER)";

    private static final String CREATE_TABLE_TAXI= "create table " + TABLE_TAXI +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "PROJEKT TEXT, " +
            "ENTFERNUNG INTEGER, " +
            "PRICE INTEGER, " +
            "MWST INTEGER)";

    private static final String CREATE_TABLE_BAHN = "create table " + TABLE_BAHN +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "PROJEKT TEXT, " +
            "ENTFERNUNG INTEGER, " +
            "PRICE INTEGER, " +
            "MWST INTEGER)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PROJEKT);
        db.execSQL(CREATE_TABLE_UNTERKUNFT);
        db.execSQL(CREATE_TABLE_AUTO);
        db.execSQL(CREATE_TABLE_FLUGZEUG);
        db.execSQL(CREATE_TABLE_TAXI);
        db.execSQL(CREATE_TABLE_BAHN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJEKT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNTERKUNFT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLUGZEUG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAXI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BAHN);
        onCreate(db);
    }


    //+++++++++++++CREATE A PROJECT++++++++++++//
    public boolean createProject(String project, String date_from, String date_to, String name, String vorname, String kostenstelle){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PROJECT, project);
        contentValues.put(KEY_DATE_FROM, date_from);
        contentValues.put(KEY_DATE_TO, date_to);
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_VORNAME, vorname);
        contentValues.put(KEY_KOSTENST, kostenstelle);
        long result = db.insert(TABLE_PROJEKT,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }


    //+++++++++++++CREATE A UNTERKUNFT++++++++++++//
    public boolean createUnterkunft(String project, int price, int steuer, int entfernung){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PROJECT, project);
        contentValues.put(KEY_PRICE, price);
        contentValues.put(KEY_MWST, steuer);
        contentValues.put(KEY_ENTFERNUNG, entfernung);
        long result = db.insert(TABLE_UNTERKUNFT,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }


    //+++++++++++++CREATE A AUTO++++++++++++//
    public boolean createAuto(String project, int price, int steuer, int entfernung){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PROJECT, project);
        contentValues.put(KEY_PRICE, price);
        contentValues.put(KEY_MWST, steuer);
        contentValues.put(KEY_ENTFERNUNG, entfernung);
        long result = db.insert(TABLE_AUTO,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    //+++++++++++++CREATE A FLUGZEUG++++++++++++//
    public boolean createFlugzeug(String project, int price, int steuer, int entfernung){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PROJECT, project);
        contentValues.put(KEY_PRICE, price);
        contentValues.put(KEY_MWST, steuer);
        contentValues.put(KEY_ENTFERNUNG, entfernung);
        long result = db.insert(TABLE_FLUGZEUG,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    //+++++++++++++CREATE A TAXI++++++++++++//
    public boolean createTaxi(String project, int price, int steuer, int entfernung){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PROJECT, project);
        contentValues.put(KEY_PRICE, price);
        contentValues.put(KEY_MWST, steuer);
        contentValues.put(KEY_ENTFERNUNG, entfernung);
        long result = db.insert(TABLE_TAXI,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    //+++++++++++++CREATE A BAHN++++++++++++//
    public boolean createBahn(String project, int price, int steuer, int entfernung){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PROJECT, project);
        contentValues.put(KEY_PRICE, price);
        contentValues.put(KEY_MWST, steuer);
        contentValues.put(KEY_ENTFERNUNG, entfernung);
        long result = db.insert(TABLE_BAHN,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    //Getting values from spinner (Drop-Down)
    public List<String> getAllProjects(){
        List<String> projects = new ArrayList<String>();

        String selectQuery = "SELECT " + KEY_PROJECT + " FROM " + TABLE_PROJEKT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                projects.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return projects;
    }

}
