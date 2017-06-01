package com.cmps115.trades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SellPage extends AppCompatActivity {

    public Button submitButton;
    public void initSubmit(){
        submitButton = (Button) findViewById(R.id.Submit_Button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showbuysellpage = new Intent(SellPage.this, BuySell.class);
                startActivity(showbuysellpage);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_page);
        initSubmit();
    }
}
