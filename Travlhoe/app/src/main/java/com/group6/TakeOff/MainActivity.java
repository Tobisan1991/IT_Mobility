package com.group6.TakeOff;

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

        btnTransport = (Button) findViewById(R.id.button1);
        btnAllgemein = (Button) findViewById(R.id.button2);
        btnUnterkunft = (Button) findViewById(R.id.button3);
        btnRechnung = (Button) findViewById(R.id.button4);

        btnTransport.setOnClickListener(this);
        btnRechnung.setOnClickListener(this);
        btnAllgemein.setOnClickListener(this);
        btnUnterkunft.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            startActivity(new Intent(MainActivity.this, activity_allgemein.class));
        } else if (v.getId() == R.id.button2) {
            startActivity(new Intent(MainActivity.this, activity_transport.class));
        } else if (v.getId() == R.id.button3) {
            startActivity(new Intent(MainActivity.this, activity_unterkunft.class));
        } else if (v.getId() == R.id.button4) {
            startActivity(new Intent(MainActivity.this, activity_rechnung.class));
        }
    }
}
