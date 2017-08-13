package com.group6.TakeOff;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class activity_flugzeug extends AppCompatActivity{

    DatabaseHelper myDb;
    Button btn_save;
    Spinner ChooseProject;
    EditText Entfernung,Price,MWST;
    String selectedspinner;


    private BottomNavigationViewEx bottomNavigationViewEx;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flugzeug);
        myDb = new DatabaseHelper(this);

        ChooseProject = (Spinner) findViewById(R.id.ChooseProject);
        Entfernung = (EditText) findViewById(R.id.Entfernung);
        Price = (EditText) findViewById(R.id.Preis);
        MWST = (EditText) findViewById(R.id.MwSt);
        btn_save=(Button) findViewById(R.id.btn_save);
        loadSpinnerData();
        SaveData();


        ImageButton btnCamera= (ImageButton)findViewById(R.id.btnCamera);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        //++++++++++++BOTTOM NAVIGATION BAR++++++++++++//
        bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationView);

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                if (item.getItemId()==R.id.menu_start){
                    startActivity(new Intent(activity_flugzeug.this, MainActivity.class));
                } else if(item.getItemId()==R.id.menu_allgemein){
                    startActivity(new Intent(activity_flugzeug.this, activity_allgemein.class));
                } else if(item.getItemId()==R.id.menu_rechnung){
                    startActivity(new Intent(activity_flugzeug.this, activity_rechnung.class));
                } else if(item.getItemId()==R.id.menu_unterkunft){
                    startActivity(new Intent(activity_flugzeug.this, activity_unterkunft.class));
                }
                return true;
            }
        });

        bottomNavigationViewEx.setSelectedItemId(R.id.menu_transport);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
    }

    public void onButtonClicked(View v){
        if(v.getId()==R.id.btnCamera) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            String pictureName= getPictureName();
            File imageFile=  new File(pictureDirectory,pictureName);

            //URI weil putExtra sonst nicht mit File elementen klar kommt!
            Uri pictureUri = Uri.fromFile(imageFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);

            startActivityForResult(intent, 0);
        }
        if(v.getId()==R.id.goToGPS) {
            Intent intent2 = new Intent(activity_flugzeug.this, function_gps.class );
            startActivity(intent2);
        }
    }
    private String getPictureName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        return "Rechnung"+ timestamp + ".jpg";
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Bitmap bitmap = (Bitmap)data.getExtras().get("data");

        //imageView.setImageBitmap(bitmap);
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
                        boolean isInserted = myDb.createFlugzeug(
                                selectedspinner,
                                Integer.valueOf(Price.getText().toString()),
                                Integer.valueOf(MWST.getText().toString()),
                                Integer.valueOf(Entfernung.getText().toString())
                        );
                        if(isInserted=true)
                            Toast.makeText(activity_flugzeug.this, "Daten gespeichert", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(activity_flugzeug.this, "Daten nicht gespeichert", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }




}
