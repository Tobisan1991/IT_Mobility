package com.group6.travlhoe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class activity_auto extends AppCompatActivity {

    ImageView imageView;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);

        ImageButton btnCamera= (ImageButton)findViewById(R.id.btnCamera);
        imageView=(ImageView)findViewById(R.id.imageView);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                if (item.getItemId()==R.id.menu_start){
                    startActivity(new Intent(activity_auto.this, MainActivity.class));
                } else if(item.getItemId()==R.id.menu_allgemein){
                    startActivity(new Intent(activity_auto.this, activity_allgemein.class));
                } else if(item.getItemId()==R.id.menu_rechnung){
                    startActivity(new Intent(activity_auto.this, activity_rechnung.class));
                } else if(item.getItemId()==R.id.menu_unterkunft){
                    startActivity(new Intent(activity_auto.this, activity_unterkunft.class));
                }
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.menu_transport);
    }

    public void onButtonClicked(View v){
        if(v.getId()==R.id.btnCamera) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 0);
        }
        if(v.getId()==R.id.goToGPS) {
            Intent intent2 = new Intent(activity_auto.this, function_gps.class );
            startActivity(intent2);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = (Bitmap)data.getExtras().get("data");

        imageView.setImageBitmap(bitmap);
    }
}
