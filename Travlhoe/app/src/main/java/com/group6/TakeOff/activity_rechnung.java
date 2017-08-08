package com.group6.TakeOff;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by STzavelas on 24.06.17.
 */

public class activity_rechnung extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    RecyclerView recyclerView;
    DatabaseHelper myDB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechnung);

        myDB = new DatabaseHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        Cursor data = myDB.getListContents();
        List<Trip> trip = new ArrayList<>();
        int i = 0;
        if(data.getCount() != 0){
            while(data.moveToNext()){
                Trip project = new Trip(data.getString(0),data.getString(1),data.getString(2),data.getString(3));
                trip.add(i,project);
                i++;
            }
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(trip);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        }else{
            Toast.makeText(activity_rechnung.this, "No data in DB", Toast.LENGTH_LONG).show();
        }

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                if (item.getItemId()==R.id.menu_start){
                    startActivity(new Intent(activity_rechnung.this, MainActivity.class));
                } else if(item.getItemId()==R.id.menu_allgemein){
                    startActivity(new Intent(activity_rechnung.this, activity_allgemein.class));
                } else if(item.getItemId()==R.id.menu_transport){
                    startActivity(new Intent(activity_rechnung.this, activity_transport.class));
                } else if(item.getItemId()==R.id.menu_rechnung){
                    startActivity(new Intent(activity_rechnung.this, activity_rechnung.class));
                } else if(item.getItemId()==R.id.menu_unterkunft){
                    startActivity(new Intent(activity_rechnung.this, activity_unterkunft.class));
                }
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.menu_rechnung);
    }
}
