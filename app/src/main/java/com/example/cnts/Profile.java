package com.example.cnts;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

public class Profile extends AppCompatActivity {
    Button callButton,sendSMS,addDonation;
    String uid;
    private DatabaseReference mDatabase;
    Donner DonnerData;
    public ArrayList<Donnation> Donations = new ArrayList<Donnation>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        DonnationAdapter adapter = new DonnationAdapter(this, Donations);
        ListView list = (ListView) findViewById(R.id.donation_list);
        list.setAdapter(adapter);

        Bundle b = getIntent().getExtras();
        if(b != null) uid = b.getString("uid");
        ValueEventListener donnerListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<HashMap<String,Donner>> t=  new GenericTypeIndicator<HashMap<String,Donner>>() { };
                HashMap<String, Donner> data = dataSnapshot.child("Donners").getValue(t);
                DonnerData = data.get(uid);
                TextView name = findViewById(R.id.nameDonnerProfile);
                TextView bloodtype =  findViewById(R.id.bloodtype);
                TextView donations =  findViewById(R.id.donations);
                TextView weight =  findViewById(R.id.weight);
                TextView height = findViewById(R.id.height);
                name.setText(DonnerData.getName());
                bloodtype.setText(DonnerData.getBloodType());
                donations.setText(String.valueOf(DonnerData.getDonated()));
                weight.setText(String.valueOf(DonnerData.getWeight()));
                height.setText(String.valueOf(DonnerData.getHeight()));
                GenericTypeIndicator<HashMap<String,Donnation>> donnationsGeneric  =  new GenericTypeIndicator<HashMap<String,Donnation>>() { };
                HashMap<String, Donnation> donationsData = dataSnapshot.child("Donations").getValue(donnationsGeneric);
                HashMap<String, Donnation> filteredDonationsData = (HashMap<String, Donnation>) donationsData
                        .entrySet()
                        .stream()
                        .filter(donation -> uid.equals(donation.getValue().getUserUID()))
                        .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
                ArrayList<Donnation> valuesList = new ArrayList<Donnation>(filteredDonationsData.values());
                Donations.clear();
                Donations.addAll(valuesList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("loadPost:onCancelled", databaseError.toException());
            }
        };
        mDatabase = FirebaseDatabase.getInstance("https://cnts-da2eb-default-rtdb.firebaseio.com").getReference();
        mDatabase.addValueEventListener(donnerListener);

        callButton = (Button)findViewById(R.id.call);
        callButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V) {
                String s = "tel:"+"Number";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(s));
                startActivity(intent);
            }
        });
        sendSMS = (Button)findViewById(R.id.request);
        sendSMS.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.putExtra("sms_body", "default content");
                sendIntent.setType("vnd.android-dir/mms-sms");
                startActivity(sendIntent);
            }
        });
        addDonation = (Button)findViewById(R.id.addDonation);
        addDonation.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View V) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/mm/yyyy");
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("HH:mm");
                LocalDateTime nowTime = LocalDateTime.now();
                DonnerData.setDonated(DonnerData.getDonated() + 1);
                mDatabase.child("Donners").child(uid).setValue(DonnerData);
                Donnation donationData = new Donnation("Center Transfusion Sanguine - Rabat", dtf.format(now), dtfTime.format(nowTime), uid);
                mDatabase.child("Donations").child(String.valueOf(UUID.randomUUID())).setValue(donationData);
            }
        });
    }
}