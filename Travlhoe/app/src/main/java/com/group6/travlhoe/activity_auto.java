package com.group6.travlhoe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class activity_auto extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);

        Button btnCamera= (Button)findViewById(R.id.btnCamera);
        imageView=(ImageView)findViewById(R.id.imageView);
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