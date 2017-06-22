package com.group6.travlhoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class activity_transport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
    }

    public void onButtonClicked(View v){
        if(v.getId()==R.id.Auto){
        Intent i = new Intent(activity_transport.this, activity_auto.class);
        startActivity(i);
        }

    }
}
