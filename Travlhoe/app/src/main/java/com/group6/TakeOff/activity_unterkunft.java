package com.group6.TakeOff;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.List;

/**
 * Created by STzavelas on 24.06.17.
 */

public class activity_unterkunft extends AppCompatActivity {

    DatabaseHelper myDb;
    Button btn_save;
    Spinner ChooseProject;
    EditText Entfernung,Price,MWST;
    String selectedspinner;


    private BottomNavigationViewEx bottomNavigationViewEx;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unterkunft);
        myDb = new DatabaseHelper(this);

        ChooseProject = (Spinner) findViewById(R.id.ChooseProject);
        Entfernung = (EditText) findViewById(R.id.Entfernung);
        Price = (EditText) findViewById(R.id.Preis);
        MWST = (EditText) findViewById(R.id.MwSt);
        btn_save=(Button) findViewById(R.id.btn_save);
        loadSpinnerData();
        SaveData();


        //++++++++++++BOTTOM NAVIGATION BAR++++++++++++//
        bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationView);

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                if (item.getItemId()==R.id.menu_start){
                    startActivity(new Intent(activity_unterkunft.this, MainActivity.class));
                } else if(item.getItemId()==R.id.menu_allgemein){
                    startActivity(new Intent(activity_unterkunft.this, activity_allgemein.class));
                } else if(item.getItemId()==R.id.menu_transport){
                    startActivity(new Intent(activity_unterkunft.this, activity_transport.class));
                } else if(item.getItemId()==R.id.menu_rechnung){
                    startActivity(new Intent(activity_unterkunft.this, activity_rechnung.class));
                } else if(item.getItemId()==R.id.menu_unterkunft){
                    startActivity(new Intent(activity_unterkunft.this, activity_unterkunft.class));
                }
                return true;
            }
        });

        bottomNavigationViewEx.setSelectedItemId(R.id.menu_unterkunft);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
    }

    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerData() {
        // database handler
        DatabaseHelper db = new DatabaseHelper (getApplicationContext());

        // Spinner Drop down elements
        List<String> projects = db.getAllProjects();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, projects);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ChooseProject.setPrompt("Projekt auswählen");

        // attaching data adapter to spinner
        ChooseProject.setAdapter(dataAdapter);

        //Listener für den Spinner damit ich den Wert abspeichern kann
        ChooseProject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //selectedspinner =String.ValueOf(parent.getItemAtPosition(pos));
                selectedspinner = (String) ChooseProject.getSelectedItem();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }


    //++++++++++++Save Data++++++//
    public void SaveData(){
        btn_save.setOnClickListener(
        new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.createUnterkunft(
                                selectedspinner,
                                Integer.valueOf(Price.getText().toString()),
                                Integer.valueOf(MWST.getText().toString()),
                                Integer.valueOf(Entfernung.getText().toString())
                        );
                        if(isInserted=true)
                            Toast.makeText(activity_unterkunft.this, "Daten gespeichert", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(activity_unterkunft.this, "Daten nicht gespeichert", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
}
