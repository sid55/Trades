package com.cmps115.trades;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MyProfile extends AppCompatActivity {

    public Button editprofilebutton;
    public void initEditProfile(){
        editprofilebutton = (Button) findViewById(R.id.editprofile);
        editprofilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showeditpage = new Intent(MyProfile.this, EditMyProfile.class);
                startActivity(showeditpage);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        initEditProfile();
    }

}
