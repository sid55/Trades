package com.cmps115.trades;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.*;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.graphics.Bitmap;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileLast extends AppCompatActivity {

    public final Map<String, ProfileEntry> users = new HashMap<String, ProfileEntry>();
    private static final int SELECTED_PICTURE = 100;

    //View Refs
    private ImageView imageView;
    private Button button;
    private Button saveProf;
    private Uri imageUri;

    //for getting location
    private TextView textView;
    private LocationManager locationManager;
    private LocationListener locationListener;

    //String values
    private String editFirstName;
    private String editLastName;
    private String emailName;
    private String phoneName;
    private Bitmap profPic;
    private double latitude;
    private double longitude;

    //Database Refs
    private FirebaseDatabase mDatabase;
    private DatabaseReference mProfileRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_profile_last);

        //View Refs
        imageView = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.imageSubmit);
        saveProf = (Button) findViewById(R.id.submit);

        //For location API
        textView = (TextView) findViewById(R.id.textView2);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                textView.setText("Lat: " + location.getLatitude() + "Long: " + location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);
                return;
            }
        } else {
            configureButton();
        }

        //Database References
        mDatabase = FirebaseDatabase.getInstance();
        mProfileRef = mDatabase.getReference().child("profiles");

        saveProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Even though there is an error, the permission check is taken care of
                //in the above code. Thus, there will never be an error here.

                locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);


                startActivity(new Intent(ProfileLast.this, BuySell.class));

                EditText editFirst = (EditText) findViewById(R.id.firstName);
                editFirstName = editFirst.getText().toString();

                EditText editLast = (EditText) findViewById(R.id.lastName);
                editLastName = editLast.getText().toString();

                EditText email = (EditText) findViewById(R.id.email);
                emailName = email.getText().toString();

                EditText phone = (EditText) findViewById(R.id.phone);
                phoneName = phone.getText().toString();

                ProfileEntry newUser= new ProfileEntry(editFirstName, editLastName, emailName, phoneName);
                users.put(emailName, newUser);
                mProfileRef.setValue(users);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configureButton();
                return;
        }
    }

    public void configureButton() {
        saveProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Even though there is an error, the permission check is taken care of
                //at the place the method is called

                locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
            }
        });
    }

    public void openGallery(){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, SELECTED_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
        if(resultCode == RESULT_OK && requestCode == SELECTED_PICTURE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            profPic = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
        }}
        catch (IOException ie) {
            //fill error check for bitmap conversion
        }
    }
}
