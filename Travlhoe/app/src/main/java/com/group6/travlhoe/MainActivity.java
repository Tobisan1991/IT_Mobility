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


    /*
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };
    */

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

        //mTextMessage = (TextView) findViewById(R.id.message);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void onButtonClicked(View v){
        if(v.getId()==R.id.Transport){
            Intent i = new Intent(MainActivity.this, activity_Transport.class);
            startActivity(i);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Allgemein) {
            startActivity(new Intent(MainActivity.this, activity_Allgemein.class));
        } else if (v.getId() == R.id.Transport) {
            startActivity(new Intent(MainActivity.this, activity_Transport.class));
        } else if (v.getId() == R.id.Unterkunft) {
            startActivity(new Intent(MainActivity.this, activity_Unterkunft.class));
        } else if (v.getId() == R.id.Rechnung) {
            startActivity(new Intent(MainActivity.this, activity_Rechnung.class));
        }
    }
}
