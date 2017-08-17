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
    public static final String TABLE_TRANSPORT = "transport";


    //Common column names
    public static final String KEY_PROJECT = "PROJEKT";

    //create project column names
    public static final String KEY_DATE_FROM= "DATE_FROM";
    public static final String KEY_DATE_TO= "DATE_TO";
    public static final String KEY_NAME = "NACHNAME";
    public static final String KEY_VORNAME = "VORNAME";
    public static final String KEY_KOSTENST = "KOSTENSTELLE";

    //Expenses column names
    public static final String KEY_ENTFERNUNG= "ENTFERNUNG";
    public static final String KEY_TRANSPORT= "TRANSPORT";
    public static final String KEY_PRICE= "PRICE";
    public static final String KEY_MWST= "MWST";
    public static final String KEY_RECHNUNG_IMG= "RECHNUNG_IMG";


    //CREATE TABLE STATEMENTS
    private static final String CREATE_TABLE_PROJEKT = "create table " + TABLE_PROJEKT +
            "(PROJEKT TEXT PRIMARY KEY, " +
            "DATE_FROM DATE, " +
            "DATE_TO DATE, " +
            "NACHNAME VARCHAR2, " +
            "VORNAME VARCHAR2, " +
            "KOSTENSTELLE VARCHAR2)";

    private static final String CREATE_TABLE_UNTERKUNFT = "create table " + TABLE_UNTERKUNFT +
            "(PROJEKT VARCHAR2, " +
            "ENTFERNUNG DOUBLE, " +
            "PRICE DOUBLE, " +
            "MWST DOUBLE,"+
            "RECHNUNG_IMG BLOB)";

    private static final String CREATE_TABLE_TRANSPORT = "create table " + TABLE_TRANSPORT +
            "(PROJEKT VARCHAR2, " +
            "TRANSPORT VARCHAR2, " +
            "ENTFERNUNG DOUBLE, " +
            "PRICE DOUBLE, " +
            "MWST DOUBLE, "+
            "RECHNUNG_IMG BLOB)";                                                                   //Image

    /*private static final String CREATE_TABLE_FLUGZEUG = "create table " + TABLE_FLUGZEUG +
            "(PROJEKT VARCHAR2, " +
            "ENTFERNUNG DOUBLE, " +
            "PRICE DOUBLE, " +
            "MWST DOUBLE,"+
            "RECHNUNG_IMG BLOB)";

    private static final String CREATE_TABLE_TAXI= "create table " + TABLE_TAXI +
            "(PROJEKT VARCHAR2, " +
            "ENTFERNUNG DOUBLE, " +
            "PRICE DOUBLE, " +
            "MWST DOUBLE,"+
            "RECHNUNG_IMG BLOB)";

    private static final String CREATE_TABLE_BAHN = "create table " + TABLE_BAHN +
            "(PROJEKT VARCHAR2, " +
            "ENTFERNUNG DOUBLE, " +
            "PRICE DOUBLE, " +
            "MWST DOUBLE,"+
            "RECHNUNG_IMG BLOB)";*/


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PROJEKT);
        db.execSQL(CREATE_TABLE_UNTERKUNFT);
        db.execSQL(CREATE_TABLE_TRANSPORT);
        /*db.execSQL(CREATE_TABLE_AUTO);
        db.execSQL(CREATE_TABLE_FLUGZEUG);
        db.execSQL(CREATE_TABLE_TAXI);
        db.execSQL(CREATE_TABLE_BAHN);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJEKT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNTERKUNFT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSPORT);
        /*db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLUGZEUG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAXI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BAHN);*/
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
    public boolean createUnterkunft(String project, int price, int steuer, int entfernung, byte [] image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PROJECT, project);
        contentValues.put(KEY_PRICE, price);
        contentValues.put(KEY_MWST, steuer);
        contentValues.put(KEY_ENTFERNUNG, entfernung);
        contentValues.put(KEY_RECHNUNG_IMG, image);
        long result = db.insert(TABLE_UNTERKUNFT,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }


    //+++++++++++++CREATE A TRANSPORT++++++++++++//
    public boolean createTransport(String project, int price, int steuer, int entfernung, byte[] image){  //Image
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PROJECT, project);
        contentValues.put(KEY_PRICE, price);
        contentValues.put(KEY_MWST, steuer);
        contentValues.put(KEY_ENTFERNUNG, entfernung);
        contentValues.put(KEY_RECHNUNG_IMG, image);                                                    //Image
        long result = db.insert(TABLE_TRANSPORT,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }



    /*//+++++++++++++CREATE A FLUGZEUG++++++++++++//
    public boolean createFlugzeug(String project, int price, int steuer, int entfernung, byte [] image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PROJECT, project);
        contentValues.put(KEY_PRICE, price);
        contentValues.put(KEY_MWST, steuer);
        contentValues.put(KEY_ENTFERNUNG, entfernung);
        contentValues.put(KEY_RECHNUNG_IMG, image);
        long result = db.insert(TABLE_FLUGZEUG,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }*/

    /*//+++++++++++++CREATE A TAXI++++++++++++//
    public boolean createTaxi(String project, int price, int steuer, int entfernung, byte [] image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PROJECT, project);
        contentValues.put(KEY_PRICE, price);
        contentValues.put(KEY_MWST, steuer);
        contentValues.put(KEY_ENTFERNUNG, entfernung);
        contentValues.put(KEY_RECHNUNG_IMG, image);
        long result = db.insert(TABLE_TAXI,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }*/

    /*//+++++++++++++CREATE A BAHN++++++++++++//
    public boolean createBahn(String project, int price, int steuer, int entfernung, byte [] image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PROJECT, project);
        contentValues.put(KEY_PRICE, price);
        contentValues.put(KEY_MWST, steuer);
        contentValues.put(KEY_ENTFERNUNG, entfernung);
        contentValues.put(KEY_RECHNUNG_IMG, image);
        long result = db.insert(TABLE_BAHN,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }*/

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




    //Getting data for Recycler View//
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + TABLE_PROJEKT + "." + KEY_PROJECT + " , " +
                "sum( "+ "IFNULL(" + TABLE_TRANSPORT + "." + KEY_PRICE + ",0)  + " +
                "IFNULL(" + TABLE_UNTERKUNFT + "." + KEY_PRICE + ",0)"  +
                ")" +
                " , " + KEY_DATE_FROM + " , " + KEY_DATE_TO + " FROM " + TABLE_PROJEKT +
                " left outer join " + TABLE_TRANSPORT + " on " + "(" + TABLE_PROJEKT  + "." + KEY_PROJECT + "=" + TABLE_TRANSPORT + "." + KEY_PROJECT + " ) " +
                " left outer join " + TABLE_UNTERKUNFT + " on " + "(" + TABLE_PROJEKT + "." + KEY_PROJECT + "=" + TABLE_UNTERKUNFT + "." + KEY_PROJECT + " ) " +
                "group by " + TABLE_PROJEKT + "." + KEY_PROJECT + "," + TABLE_PROJEKT + "." + KEY_DATE_FROM + "," + TABLE_PROJEKT + "." + KEY_DATE_TO, null);
        return data;
    }





}
