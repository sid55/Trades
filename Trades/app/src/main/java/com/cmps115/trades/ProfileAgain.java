package com.cmps115.trades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ProfileAgain extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_again);

        Button orderButton = (Button)findViewById(R.id.button2);

        orderButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent pickIntent = new Intent();
                pickIntent.setType("image/*");
                pickIntent.setAction(Intent.ACTION_GET_CONTENT);

                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                String pickTitle = "Select or take a new Picture"; // Or get from strings.xml
                Intent chooserIntent = Intent.createChooser(pickIntent, pickTitle);
                chooserIntent.putExtra
                        (
                                Intent.EXTRA_INITIAL_INTENTS,
                                new Intent[] { takePhotoIntent }
                        );

                startActivityForResult(chooserIntent, SELECT_PICTURE);

            }

        });
    }
}
