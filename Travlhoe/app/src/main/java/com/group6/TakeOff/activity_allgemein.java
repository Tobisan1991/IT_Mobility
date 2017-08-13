package com.group6.TakeOff;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.text.InputType;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by STzavelas on 24.06.17.
 */

public class activity_allgemein extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper myDb;

    private BottomNavigationViewEx bottomNavigationViewEx;

    EditText CreateProject, eTxt_DatumVon, eTxt_DatumBis, Nachname, Vorname, Kostenstelle;
    Button btn_save;

    private EditText DatumVon;
    private EditText DatumBis;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allgemein);
        myDb = new DatabaseHelper(this);

        CreateProject = (EditText) findViewById(R.id.CreateProject);
        eTxt_DatumVon = (EditText) findViewById(R.id.eTxt_DatumVon);
        eTxt_DatumBis = (EditText) findViewById(R.id.eTxt_DatumBis);
        Nachname = (EditText) findViewById(R.id.Nachname);
        Vorname = (EditText) findViewById(R.id.Vorname);
        Kostenstelle= (EditText) findViewById(R.id.Kostenstelle);
        btn_save=(Button) findViewById(R.id.btn_save);
        SaveData();

        //+++++++++++BOTTOM NAVIGATION BAR++++++++++++++//
        bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationView);

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                if (item.getItemId()==R.id.menu_start){
                    startActivity(new Intent(activity_allgemein.this, MainActivity.class));
                } else if(item.getItemId()==R.id.menu_allgemein){
                    startActivity(new Intent(activity_allgemein.this, activity_allgemein.class));
                } else if(item.getItemId()==R.id.menu_transport){
                    startActivity(new Intent(activity_allgemein.this, activity_transport.class));
                } else if(item.getItemId()==R.id.menu_rechnung){
                    startActivity(new Intent(activity_allgemein.this, activity_rechnung.class));
                } else if(item.getItemId()==R.id.menu_unterkunft){
                    startActivity(new Intent(activity_allgemein.this, activity_unterkunft.class));
                }
                return true;
            }
            });

        bottomNavigationViewEx.setSelectedItemId(R.id.menu_allgemein);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);


        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);

        findViewsById();

        setDateTimeField();

    }

    public void SaveData(){
        btn_save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.createProject(
                                CreateProject.getText().toString(),
                                eTxt_DatumVon.getText().toString(),
                                eTxt_DatumBis.getText().toString(),
                                Nachname.getText().toString(),
                                Vorname.getText().toString(),
                                Kostenstelle.getText().toString());
                        if(isInserted=true)
                            Toast.makeText(activity_allgemein.this, "Daten gespeichert", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(activity_allgemein.this, "Daten nicht gespeichert", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    private void findViewsById() {
        DatumVon = (EditText) findViewById(R.id.eTxt_DatumVon);
        DatumVon.setInputType(InputType.TYPE_NULL);
        DatumVon.requestFocus();

        DatumBis = (EditText) findViewById(R.id.eTxt_DatumBis);
        DatumBis.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField(){
        DatumVon.setOnClickListener(this);
        DatumBis.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                DatumVon.setText(dateFormatter.format(newDate.getTime()));

            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                DatumBis.setText(dateFormatter.format(newDate.getTime()));

            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public void onClick(View view) {

        if(view == DatumVon) {
            fromDatePickerDialog.show();
        } else if(view == DatumBis) {
            toDatePickerDialog.show();
        }

    }
}
