package com.cmps115.trades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity {

    //View Refs
    private ImageView imageView;
    private TextView name;
    private TextView email;
    private TextView phone;

    /*
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
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        //initEditProfile();

        //View Refs
        imageView = (ImageView) findViewById(R.id.image);
        name = (TextView) findViewById(R.id.first);
        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.phonenumber);

        name.setText(ProfileLast.currUser.getName());
        email.setText(ProfileLast.currUser.getEmail()+".edu");
        phone.setText(ProfileLast.currUser.getPhone());
    }
}
