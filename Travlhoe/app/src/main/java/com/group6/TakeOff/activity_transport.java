package com.group6.TakeOff;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.group6.TakeOff.R.id.ChooseTransport;
import static com.group6.TakeOff.R.id.bottomNavigationView;
import static com.group6.TakeOff.R.id.parent;

public class activity_transport extends AppCompatActivity {

    private BottomNavigationViewEx bottomNavigationViewEx;
    DatabaseHelper myDb;
    Button btn_save;
    Spinner ChooseProject,ChooseTransport;
    EditText Entfernung,Price,MWST;
    String selectedspinner, selectedTransport;

    ImageView imageView2;
    private static int PICK_IMAGE = 100;
    Uri imageUri;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        myDb = new DatabaseHelper(this);

        ChooseProject = (Spinner) findViewById(R.id.ChooseProject);
        ChooseTransport = (Spinner) findViewById(R.id.ChooseTransport);



        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.transport, android.R.layout.simple_spinner_dropdown_item);
        //hooseTransport.setAdapter(adapter);
        //ChooseTransport.setOnItemSelectedListener(this);


        Entfernung = (EditText) findViewById(R.id.Entfernung);
        Price = (EditText) findViewById(R.id.Preis);
        MWST = (EditText) findViewById(R.id.MwSt);
        btn_save=(Button) findViewById(R.id.btn_save);
        //bildanzeigebereich im layout
        imageView2=(ImageView) findViewById(R.id.imageView2);
        loadProjectSpinnerData();
        loadTransportSpinnerData();
        SaveData();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(bottomNavigationView);

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                if (item.getItemId()==R.id.menu_start){
                    startActivity(new Intent(activity_transport.this, MainActivity.class));
                } else if(item.getItemId()==R.id.menu_allgemein){
                    startActivity(new Intent(activity_transport.this, activity_allgemein.class));
                } else if(item.getItemId()==R.id.menu_rechnung){
                    startActivity(new Intent(activity_transport.this, activity_rechnung.class));
                } else if(item.getItemId()==R.id.menu_unterkunft){
                    startActivity(new Intent(activity_transport.this, activity_unterkunft.class));
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
            //Intent intent2 = new Intent(activity_transport.this, function_gps.class );
            Intent intent2 = new Intent(activity_transport.this, MapsActivity.class );
            startActivity(intent2);
        }
        if(v.getId()==R.id.btnGallery){
            openGallery();
        }
    }

    private void openGallery() {
        Intent Gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Gallery, PICK_IMAGE);
    }

    private String getPictureName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        return "Rechnung"+ timestamp + ".jpg";
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==PICK_IMAGE){
            imageUri= data.getData();
            imageView2.setImageURI(imageUri);
        }

        // Bitmap bitmap = (Bitmap)data.getExtras().get("data");

        //imageView.setImageBitmap(bitmap);
    }


    public void onItemSelected(AdapterView<?> parent, View v, int position, long id ){
        selectedTransport = (String) ChooseTransport.getSelectedItem();
    }

    public void onNothingSelected(AdapterView<?> parent){
        Toast.makeText(this, "Choose Countries :", Toast.LENGTH_SHORT).show();
    }

    //+++++++++++++++Function to load the spinner data from SQLite database++++++++++//

    private void loadProjectSpinnerData() {
        // database handler
        DatabaseHelper db = new DatabaseHelper (getApplicationContext());

        // Spinner Drop down elements
        List<String> projects = db.getAllProjects();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, projects);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ChooseProject.setPrompt("Projekt auswählen");

        // attaching data adapter to spinner
        ChooseProject.setAdapter(dataAdapter);

        //Listener für den Projekt Spinner damit ich den Wert abspeichern kann
        ChooseProject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    selectedspinner = (String) ChooseProject.getSelectedItem();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }



    private void loadTransportSpinnerData(){


        ArrayAdapter<CharSequence> spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.transport, android.R.layout.simple_spinner_dropdown_item);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ChooseTransport.setPrompt("Transport auswählen");
        ChooseTransport.setAdapter(spinnerArrayAdapter);

        ChooseTransport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedTransport = (String) ChooseTransport.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }



    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    //++++++++++++Save Data++++++//
    public void SaveData(){
        btn_save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.createTransport(
                                selectedTransport,
                                selectedspinner,
                                Integer.valueOf(Price.getText().toString()),
                                Integer.valueOf(MWST.getText().toString()),
                                Integer.valueOf(Entfernung.getText().toString()),
                                imageViewToByte(imageView2)
                        );
                        if(isInserted=true)
                            Toast.makeText(activity_transport.this, "Daten gespeichert", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(activity_transport.this, "Daten nicht gespeichert", Toast.LENGTH_LONG).show();

                        Price.setText("");
                        Entfernung.setText("");
                        MWST.setText("");

                    }
                }
        );
    }


}
