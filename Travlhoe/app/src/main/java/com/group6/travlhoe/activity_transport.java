package com.group6.travlhoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_Transport extends AppCompatActivity implements View.OnClickListener {

    Button btnAuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        btnAuto = (Button) findViewById(R.id.Auto);
        btnAuto.setOnClickListener(this);
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
