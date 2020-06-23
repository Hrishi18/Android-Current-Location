package com.example.getcurrentlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private TextView textLatLong;
    private ProgressBar progressBar;

    

    public static double latitude;
    public static double longitude;
    private static double latitude1=19.0744;
    private static double longitude1=72.8605;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textLatLong = findViewById(R.id.textLatLong);
        progressBar = findViewById(R.id.progressBar);
       // textDistance = findViewById(R.id.textDistance);

        /*latitude1 =getResources().getStringArray(R.array.latitude_one);
        longitude1 =getResources().getStringArray(R.array.longitude_one);*/

        findViewById(R.id.buttonGetCurrentLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_LOCATION_PERMISSION);
                }
                else{
                    getCurrentLocation();
                }

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        MyAdapter adapter = new MyAdapter(this, distanceModel.getObjectList());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if( requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();

            }
            else {
                Toast.makeText(this, "Permission Denied!",Toast.LENGTH_SHORT).show();
            }
        }
    }



    private void getCurrentLocation(){
        progressBar.setVisibility(View.VISIBLE);

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(MainActivity.this).requestLocationUpdates(locationRequest, new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                LocationServices.getFusedLocationProviderClient(MainActivity.this).removeLocationUpdates(this);
                if (locationResult != null && locationResult.getLocations().size() > 0){
                    int latestLocationIndex = locationResult.getLocations().size()-1;
                    latitude = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                    longitude = locationResult.getLocations().get(latestLocationIndex).getLongitude();


                    distance(latitude, longitude, latitude1, longitude1);






                }
                progressBar.setVisibility(View.GONE);
            }
        }, Looper.getMainLooper());


    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {



            // haversine great circle distance approximation, returns meters
            double theta = lon1 - lon2;
            double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                    + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                    * Math.cos(deg2rad(theta));
            dist = Math.acos(dist);
            dist = rad2deg(dist);
            dist = dist * 60; // 60 nautical miles per degree of seperation
            dist = dist * 1852; // 1852 meters per nautical mile

            textLatLong.setText(dist);

            return (dist);






    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


}


