package com.group6.travlhoe;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class activity_Transport extends AppCompatActivity implements View.OnClickListener {

    Button btnAuto;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        btnAuto = (Button) findViewById(R.id.Auto);
        btnAuto.setOnClickListener(this);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                if (item.getItemId()==R.id.menu_allgemein){
                    startActivity(new Intent(activity_Transport.this, activity_Allgemein.class));
                } else if(item.getItemId()==R.id.menu_transport){
                    startActivity(new Intent(activity_Transport.this, activity_Transport.class));
                } else if(item.getItemId()==R.id.menu_rechnung){
                    startActivity(new Intent(activity_Transport.this, activity_Rechnung.class));
                } else if(item.getItemId()==R.id.menu_unterkunft){
                    startActivity(new Intent(activity_Transport.this, activity_Unterkunft.class));
                }
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.menu_transport);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Auto) {
            startActivity(new Intent(activity_Transport.this, activity_Auto.class));
        }
    }

    /*
    public void onButtonClicked(View v){
        if(v.getId()==R.id.Auto){
        Intent i = new Intent(activity_Transport.this, activity_Auto.class);
        startActivity(i);
        }

    }
    */


}
