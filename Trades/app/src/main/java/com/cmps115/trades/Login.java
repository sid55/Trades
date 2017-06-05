package com.cmps115.trades;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.jar.Attributes;

public class Login extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    private LinearLayout profile;
    private ImageView picture;

    private TextView username;
    private TextView user_email;
    private SignInButton signInBtn;


    private static final String TAG = "Why is it crashing?";
    private GoogleApiClient apiClient;
    private static final int CODE = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        apiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();

        profile = (LinearLayout) findViewById(R.id.profile);



        signInBtn = (SignInButton) findViewById(R.id.signin);
        username = (TextView) findViewById(R.id.nameuser);
        user_email = (TextView) findViewById(R.id.emailuser);


        signInBtn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.signin:
                signingIn();
                break;
            case R.id.logoutBtn:
                switchpages();
                break;

        }


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);

    }


    //
    private void result(GoogleSignInResult r){
        Log.i("visible", r.isSuccess() + "");
        if(r.isSuccess()){

            GoogleSignInAccount account = r.getSignInAccount();

            String name = account.getDisplayName();
            username.setText(name);


            String email = account.getEmail();
            user_email.setText(email);

            switchpages();


        }



    }


    private void switchpages(){
        Intent i = new Intent(Login.this, Login.class);
        startActivity(i);

    }

    private void signingOut(){

        Auth.GoogleSignInApi.signOut(apiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {

            }
        });

    }

    //
    private void signingIn(){
        Intent i = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(i, CODE); //must override with onActivityResult


    }



    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent d) {
        super.onActivityResult(reqCode, resCode, d);

        if(reqCode == CODE){

            GoogleSignInResult r = Auth.GoogleSignInApi.getSignInResultFromIntent(d);
            result(r);

        }
    }
}
