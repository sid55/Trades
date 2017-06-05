package com.cmps115.trades;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyProfile extends AppCompatActivity {
    //View Refs
    private TextView vName;
    private TextView vEmail;
    private TextView vPhone;
    private TextView vRate;

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

        //View Refs
        vName = (TextView) findViewById(R.id.first);
        vEmail = (TextView) findViewById(R.id.email);
        vPhone = (TextView) findViewById(R.id.phonenumber);
        vRate = (TextView) findViewById(R.id.Rating);

        ProfileEntry currUser= ProfileLast.currUser;
        vName.setText(currUser.getname());
        vEmail.setText(currUser.getEmail());
        vPhone.setText(currUser.getPhone());
        vRate.setText(currUser.getRating());
    }

}
