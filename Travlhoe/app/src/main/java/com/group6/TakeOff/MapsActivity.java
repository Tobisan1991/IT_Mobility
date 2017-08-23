package com.group6.TakeOff;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;

import android.location.Location;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMarkerDragListener
        {

    private GoogleMap mMap;
            private GoogleApiClient client;
            private LocationRequest locationRequest;
            private Location lastLocation;
            private Marker currentLocationMarker;
            public static final int REQUEST_LOCATION_CODE=99;
            double latitude, longtitude;
            double end_latitude, end_longtitude;
            double df, dg;
            float f, g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            checkLocationPermission();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

            @Override
            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                switch(requestCode)
                {
                    case REQUEST_LOCATION_CODE:
                        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                            //permission is granted
                            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                                if(client==null){
                                    buildGoogleApiClient();
                                }
                                mMap.setMyLocationEnabled(true);
                            }
                        }else{
                            Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                        }
                        return;
                }

            }

            public void onClick(View v){
        if(v.getId()==R.id.Bsearch){

            EditText tf = (EditText) findViewById(R.id.TFaddress);
            String location = tf.getText().toString();
            List<Address> addressList;


            if(!location.equals("")) {
                Geocoder geocoder = new Geocoder(this);

                try {
                    addressList = geocoder.getFromLocationName(location, 5);

                    if(addressList != null)
                    {
                        for(int i = 0;i<addressList.size();i++)
                        {
                            LatLng latLng = new LatLng(addressList.get(i).getLatitude() , addressList.get(i).getLongitude());
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(latLng);
                            markerOptions.title(location);

                            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(14));

                            float searchResult [] = new float[10];
                            Location.distanceBetween(latitude,longtitude,latLng.latitude,latLng.longitude,searchResult);
                            markerOptions.snippet("Distance "+searchResult[0]);
                            g=searchResult[0];
                            dg=g;
                            mMap.addMarker(markerOptions);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(v.getId()==R.id.bTo){
            mMap.clear();
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(end_latitude, end_longtitude));
            markerOptions.title("Destination");
            markerOptions.draggable(true);

            float result [] = new float[10];
            Location.distanceBetween(latitude, longtitude,end_latitude,end_longtitude,result);
            markerOptions.snippet("Distance "+result[0]);
            f= result[0];
            df=f;
            mMap.addMarker(markerOptions);

        }
        if(v.getId()==R.id.bReturn){
            if(f!=0) {

                String s = String.valueOf(round((df/1000),2));
                Intent intentTransport = new Intent(MapsActivity.this, activity_transport.class);
                intentTransport.putExtra("e1", s);
                startActivity(intentTransport);
            }else if(g!=0){
                String s = String.valueOf(round((dg/1000),2));
                Intent intentTransport = new Intent(MapsActivity.this, activity_transport.class);
                intentTransport.putExtra("e1", s);
                startActivity(intentTransport);
            }else if(f==0 && g== 0){
                Intent intentTransport = new Intent(MapsActivity.this, activity_transport.class);
                startActivity(intentTransport);
            }


        }



    }
            public static double round(double value, int places) {
                if (places < 0) throw new IllegalArgumentException();

                BigDecimal bd = new BigDecimal(value);
                bd = bd.setScale(places, RoundingMode.HALF_UP);
                return bd.doubleValue();
            }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*
        LatLng frauas = new LatLng(50.129027, 8.692125);
        mMap.addMarker(new MarkerOptions().position(frauas).title("FRA UAS"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(frauas, 18));
        */
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){

            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
            }
        mMap.setOnMarkerDragListener(this);
        mMap.setOnMarkerClickListener(this);
    }

    protected synchronized void buildGoogleApiClient(){
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        client.connect();


    }

            @Override
            public void onLocationChanged(Location location) {
                latitude=location.getLatitude();
                longtitude=location.getLongitude();
                lastLocation=location;
                if(currentLocationMarker!=null){
                    currentLocationMarker.remove();
                }

                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Current Location");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                currentLocationMarker=mMap.addMarker(markerOptions);

                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomBy(14));

                if(client!= null){
                    LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
                }


            }

            @Override
            public void onConnected(@Nullable Bundle bundle) {
                locationRequest=new LocationRequest();
                locationRequest.setInterval(1000);
                locationRequest.setFastestInterval(1000);
                locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

                if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
                {
                    LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
                }

            }
            public boolean checkLocationPermission(){

                if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                    if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
                    }
                    else{
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
                    }
                    return false;
                }
                else
                    return true;
            }



            @Override
            public void onConnectionSuspended(int i) {

            }

            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

            }

            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.setDraggable(true);
                return false;
            }

            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                end_latitude=marker.getPosition().latitude;
                end_longtitude=marker.getPosition().longitude;
            }
        }
