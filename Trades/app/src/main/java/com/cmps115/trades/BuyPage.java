package com.cmps115.trades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class BuyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_page);

        ArrayList<ListingEntry> arrayOfListings = new ArrayList<ListingEntry>();
        ListingAdapter adapter = new ListingAdapter(this, arrayOfListings);

        ListView listView= (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        ProfileEntry newUse= new ProfileEntry("first", "last", "email", "phone", 123, 123, "fkjdsl");

        ListingEntry lister= new ListingEntry("skill2", newUse, "desc");
        adapter.add(lister);
        ProfileEntry newUser= new ProfileEntry("f", "l", "email", "phone", 123, 123, "dsfdsi");

        ListingEntry listerr= new ListingEntry("skill1", newUser, "desc");
        adapter.insert(listerr, 0);
    }
}
