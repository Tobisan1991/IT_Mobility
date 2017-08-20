package com.group6.TakeOff;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by STzavelas on 18.08.17.
 */

public class activity_projektDetails extends AppCompatActivity {

    FloatingActionButton fabPlus, fabCam, fabGPS;
    Animation FabOpen, FabClose, FabRotateClockwise, FabRotateAntiClockwise;
    boolean isOpen =false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projekt_details);


        fabPlus = (FloatingActionButton) findViewById(R.id.floatingButton);
        fabCam = (FloatingActionButton) findViewById(R.id.floatingButtonCam);
        fabGPS = (FloatingActionButton) findViewById(R.id.floatingButtonGPS);

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        FabRotateClockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        FabRotateAntiClockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);

        fabPlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(isOpen){

                    fabCam.startAnimation(FabClose);
                    fabGPS.startAnimation(FabClose);
                    fabPlus.startAnimation(FabRotateAntiClockwise);
                    fabCam.setClickable(false);
                    fabGPS.setClickable(false);
                    isOpen=false;

                }else{
                    fabCam.startAnimation(FabOpen);
                    fabGPS.startAnimation(FabOpen);
                    fabPlus.startAnimation(FabRotateClockwise);
                    fabCam.setClickable(true);
                    fabGPS.setClickable(true);
                    isOpen=true;
                }
            }
        });


    }
}
