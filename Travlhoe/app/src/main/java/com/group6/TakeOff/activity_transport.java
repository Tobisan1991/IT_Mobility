package com.group6.TakeOff;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by STzavelas on 29.06.17.
 */

public class activity_transport extends Activity implements View.OnClickListener{

    Button btnAuto, btnFlugzeug, btnTaxi, btnZug;
    private static final String TAG = "Transport";
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        btnAuto = (Button) findViewById(R.id.Auto);
        btnFlugzeug = (Button) findViewById(R.id.Flugzeug);
        btnTaxi = (Button) findViewById(R.id.Taxi);
        btnZug= (Button) findViewById(R.id.Bahn);


        btnAuto.setOnClickListener(this);
        btnFlugzeug.setOnClickListener(this);
        btnTaxi.setOnClickListener(this);
        btnZug.setOnClickListener(this);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                if (item.getItemId()==R.id.menu_start){
                    startActivity(new Intent(activity_transport.this, MainActivity.class));
                } else if(item.getItemId()==R.id.menu_allgemein){
                    startActivity(new Intent(activity_transport.this, activity_allgemein.class));
                } else if(item.getItemId()==R.id.menu_transport){
                    startActivity(new Intent(activity_transport.this, activity_transport.class));
                } else if(item.getItemId()==R.id.menu_rechnung){
                    startActivity(new Intent(activity_transport.this, activity_rechnung.class));
                } else if(item.getItemId()==R.id.menu_unterkunft){
                    startActivity(new Intent(activity_transport.this, activity_unterkunft.class));
                }
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.menu_transport);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Auto:
                Intent i = new Intent(activity_transport.this, activity_auto.class);
                startActivity(i);
                //Log.e(TAG, view.toString());
                break;
            case R.id.Flugzeug:
                Intent i1 = new Intent(activity_transport.this, activity_flugzeug.class);
                startActivity(i1);
                break;
            case R.id.Bahn:
                Intent i2 = new Intent(activity_transport.this, activity_zug.class);
                startActivity(i2);
                break;
            case R.id.Taxi:
                Intent i3 = new Intent(activity_transport.this, activity_taxi.class);
                startActivity(i3);
                break;

        }

    }
}
