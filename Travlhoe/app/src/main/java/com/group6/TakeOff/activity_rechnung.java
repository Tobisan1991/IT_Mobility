package com.group6.TakeOff;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by STzavelas on 24.06.17.
 */

public class activity_rechnung extends AppCompatActivity {

    private BottomNavigationViewEx bottomNavigationViewEx;

    RecyclerView recyclerView;
    DatabaseHelper myDB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechnung);

        myDB = new DatabaseHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        Cursor data = myDB.getListContents();
        final List<Trip> trip = new ArrayList<>();
        int i = 0;
        if(data.getCount() != 0){
            while(data.moveToNext()){
                Trip project = new Trip(data.getString(0),data.getDouble(1),data.getString(2),data.getString(3));
                trip.add(i,project);
                i++;
            }
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(trip);
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager((new LinearLayoutManager(this)));
            ItemTouchHelper.Callback callback = new SwipeHelper(adapter);
            ItemTouchHelper helper = new ItemTouchHelper(callback);
            helper.attachToRecyclerView(recyclerView);

        }else{
            Toast.makeText(activity_rechnung.this, "Bisher keine Projekte vorhanden", Toast.LENGTH_LONG).show();
        }

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                startActivity(new Intent(activity_rechnung.this, activity_projektDetails.class));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));





        bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationView);

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener(){
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

        bottomNavigationViewEx.setSelectedItemId(R.id.menu_rechnung);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
    }




}
