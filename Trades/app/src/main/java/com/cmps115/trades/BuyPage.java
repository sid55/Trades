package com.cmps115.trades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BuyPage extends AppCompatActivity {

    public DataSnapshot listings;

    //Database Refs
    private FirebaseDatabase mDatabase;
    private DatabaseReference mListingRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_page);

        //Database References
        mDatabase = FirebaseDatabase.getInstance();
        mListingRef = mDatabase.getReference().child("listings");

        //Listing Adapter for Buy display
        ArrayList<ListingEntry> arrayOfListings = new ArrayList<ListingEntry>();
        final ListingAdapter adapter = new ListingAdapter(this, arrayOfListings);

        ListView listView= (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        
        mListingRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ListingEntry post= dataSnapshot.getValue(ListingEntry.class);
     //           ProfileEntry newUse= post.getLister();
     //           ListingEntry lister= new ListingEntry(post.getName(), newUse, post.getDesc());
                adapter.insert(post, 0);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /*
        ProfileEntry newUse= new ProfileEntry("first", "last", "email", "phone", 123, 123);
        ListingEntry lister= new ListingEntry("skill2", newUse, "desc");
        adapter.insert(lister, 0);
        */
    }
}
