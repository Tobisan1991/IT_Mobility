package com.group6.travlhoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by STzavelas on 24.06.17.
 */

public class activity_rechnung extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechnung);

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
