package com.cmps115.trades;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ProfileLast extends AppCompatActivity {

    private static final int SELECTED_PICTURE = 100;
    ImageView imageView;
    Button button;
    Button saveProf;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_profile_last);

        imageView = (ImageView) findViewById(R.id.imageView1);
        button = (Button) findViewById(R.id.button1);
        saveProf = (Button) findViewById(R.id.button2);

        saveProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ProfileLast.this, BuySell.class));

                EditText editFirst = (EditText)findViewById(R.id.editText);
                String editFirstName = editFirst.getText().toString();

                EditText editLast = (EditText)findViewById(R.id.editText2);
                String editLastName = editLast.getText().toString();

                EditText email = (EditText)findViewById(R.id.email);
                String emailName = email.getText().toString();

                EditText phone = (EditText)findViewById(R.id.phone);
                String phoneName = phone.getText().toString();
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
        if(resultCode == RESULT_OK && requestCode == SELECTED_PICTURE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
}
