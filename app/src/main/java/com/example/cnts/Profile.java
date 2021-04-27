package com.example.cnts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Profile extends AppCompatActivity {
    Button callButton,sendSMS,addDonation;
    String uid;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Bundle b = getIntent().getExtras();
        if(b != null) uid = b.getString("uid");
        ValueEventListener donnerListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<HashMap<String,Donner>> t=  new GenericTypeIndicator<HashMap<String,Donner>>() { };
                HashMap<String, Donner> data = dataSnapshot.child("Donners").getValue(t);
                Donner DonnerData = data.get(uid);
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
            @Override
            public void onClick(View V) {
                    
            }
        });
    }
}