package com.cmps115.trades;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Daniel on 6/1/2017.
 */

public class ListingAdapter extends ArrayAdapter<ListingEntry> {
    //Constructor
    public ListingAdapter(Context context, ArrayList<ListingEntry> objects){
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ListingEntry listing = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listing, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.Name);
        name.setText(listing.getLister().getfName()+" "+listing.getLister().getlName());

        return convertView;
    }
}