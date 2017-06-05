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
import android.widget.Toast;

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
    private TrackGPS gps;
    private double latitude;
    private double longitude;

    //String values
    private String editFirstName;
    private String editLastName;
    private String emailName;
    private String phoneName;
    private Bitmap profPic;

    //Database Refs
    private FirebaseDatabase mDatabase;
    private DatabaseReference mProfileRef;
    private DatabaseReference mNewProfileRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_profile_last);

        //View Refs
        imageView = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.imageSubmit);
        saveProf = (Button) findViewById(R.id.submit);


        //Database References
        mDatabase = FirebaseDatabase.getInstance();
        mProfileRef = mDatabase.getReference().child("profiles");

        saveProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gps = new TrackGPS(ProfileLast.this);


                if(gps.canGetLocation()){


                    longitude = gps.getLongitude();
                    latitude = gps .getLatitude();

                    //Toast.makeText(getApplicationContext(),"Longitude:"+Double.toString(longitude)+"\nLatitude:"+Double.toString(latitude),Toast.LENGTH_SHORT).show();
                }
                else
                {

                    gps.showSettingsAlert();
                }



                startActivity(new Intent(ProfileLast.this, BuySell.class));

                EditText editFirst = (EditText) findViewById(R.id.firstName);
                editFirstName = editFirst.getText().toString();

                EditText editLast = (EditText) findViewById(R.id.lastName);
                editLastName = editLast.getText().toString();

                EditText email = (EditText) findViewById(R.id.email);
                emailName = email.getText().toString();

                EditText phone = (EditText) findViewById(R.id.phone);
                phoneName = phone.getText().toString();

                mNewProfileRef = mDatabase.getReference().child("profiles/"+emailName);
                ProfileEntry newUser = new ProfileEntry(editFirstName, editLastName, emailName, phoneName);
                users.put(emailName, newUser);
                mNewProfileRef.setValue(users);
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
    protected void onDestroy(){
        super.onDestroy();
        gps.stopUsingGPS();
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
