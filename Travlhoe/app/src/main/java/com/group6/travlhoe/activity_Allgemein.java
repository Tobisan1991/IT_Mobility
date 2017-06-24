package com.group6.travlhoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


/**
 * Created by STzavelas on 24.06.17.
 */

public class activity_Allgemein extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allgemein);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                if (item.getItemId()==R.id.menu_allgemein){
                    startActivity(new Intent(activity_Allgemein.this, activity_Allgemein.class));
                } else if(item.getItemId()==R.id.menu_transport){
                    startActivity(new Intent(activity_Allgemein.this, activity_Transport.class));
                } else if(item.getItemId()==R.id.menu_rechnung){
                    startActivity(new Intent(activity_Allgemein.this, activity_Rechnung.class));
                } else if(item.getItemId()==R.id.menu_unterkunft){
                    startActivity(new Intent(activity_Allgemein.this, activity_Unterkunft.class));
                }
                return true;
            }
            });

        bottomNavigationView.setSelectedItemId(R.id.menu_allgemein);

    }
}
