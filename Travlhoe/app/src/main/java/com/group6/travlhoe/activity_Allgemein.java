package com.group6.travlhoe;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.text.InputType;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by STzavelas on 24.06.17.
 */

public class activity_allgemein extends AppCompatActivity implements View.OnClickListener{


    private BottomNavigationView bottomNavigationView;

    private EditText DatumVon;
    private EditText DatumBis;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allgemein);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                if (item.getItemId()==R.id.menu_allgemein){
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

        bottomNavigationView.setSelectedItemId(R.id.menu_allgemein);


        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);

        findViewsById();

        setDateTimeField();

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
