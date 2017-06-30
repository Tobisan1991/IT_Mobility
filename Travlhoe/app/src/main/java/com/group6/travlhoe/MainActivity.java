package com.group6.travlhoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextMessage;

    Button btnTransport, btnUnterkunft, btnAllgemein, btnRechnung;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTransport = (Button) findViewById(R.id.Transport);
        btnAllgemein = (Button) findViewById(R.id.Allgemein);
        btnUnterkunft = (Button) findViewById(R.id.Unterkunft);
        btnRechnung = (Button) findViewById(R.id.Rechnung);

        btnTransport.setOnClickListener(this);
        btnRechnung.setOnClickListener(this);
        btnAllgemein.setOnClickListener(this);
        btnUnterkunft.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Allgemein) {
            startActivity(new Intent(MainActivity.this, activity_allgemein.class));
        } else if (v.getId() == R.id.Transport) {
            startActivity(new Intent(MainActivity.this, activity_transport.class));
        } else if (v.getId() == R.id.Unterkunft) {
            startActivity(new Intent(MainActivity.this, activity_unterkunft.class));
        } else if (v.getId() == R.id.Rechnung) {
            startActivity(new Intent(MainActivity.this, activity_rechnung.class));
        }
    }
}
