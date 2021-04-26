package com.example.cnts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class DonnerAdapter extends ArrayAdapter<Donner> {
    public DonnerAdapter(Context context, ArrayList<Donner> donners) {
        super(context, 0, donners);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.donner, parent, false);

        Donner donner = getItem(position);
        TextView name = (TextView) rowView.findViewById(R.id.donnerName);
        TextView bloodType = (TextView) rowView.findViewById(R.id.bloodType);
        TextView date = (TextView) rowView.findViewById(R.id.date);
        TextView donnated = (TextView) rowView.findViewById(R.id.donnated);

        name.setText(donner.Name);
        bloodType.setText(donner.BloodType);
        date.setText(donner.CreationDate);
        donnated.setText(donner.Donated.toString());

        return rowView;
    }
}
