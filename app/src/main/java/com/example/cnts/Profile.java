package com.example.cnts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Profile extends AppCompatActivity {
    Button callButton,sendSMS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        callButton = (Button)findViewById(R.id.submit);
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
    }
}