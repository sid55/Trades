package com.cmps115.trades;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileLast extends AppCompatActivity {

    private static final int SELECTED_PICTURE = 100;

    //View Refs
    private ImageView imageView;
    private Button button;
    private Button saveProf;
    private Uri imageUri;

    //String values
    private String editFirstName;
    private String editLastName;
    private String emailName;
    private String phoneName;
    private Bitmap profPic;

    //Database Refs
    private FirebaseDatabase mDatabase;
    private DatabaseReference mProfileRef;
    private DatabaseReference mListingRef;

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
        mListingRef = mDatabase.getReference().child("listings");

        saveProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ProfileLast.this, BuySell.class));

                EditText editFirst = (EditText)findViewById(R.id.firstName);
                editFirstName = editFirst.getText().toString();

                EditText editLast = (EditText)findViewById(R.id.lastName);
                editLastName = editLast.getText().toString();

                EditText email = (EditText)findViewById(R.id.email);
                emailName = email.getText().toString();

                EditText phone = (EditText)findViewById(R.id.phone);
                phoneName = phone.getText().toString();

                ProfileEntry newUser= new ProfileEntry(editFirstName, editLastName, emailName, phoneName, profPic);
                Map<String, ProfileEntry> users = new HashMap<String, ProfileEntry>();
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
