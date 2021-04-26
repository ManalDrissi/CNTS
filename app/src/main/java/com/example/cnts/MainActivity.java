package com.example.cnts;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Donner> Donners = new ArrayList<Donner>(List.of(
        new Donner("aymen", "+A", "26/04/2021", 2),
        new Donner("aymen", "+A", "26/04/2021", 2),
        new Donner("aymen", "+A", "26/04/2021", 2),
        new Donner("aymen", "+A", "26/04/2021", 2),
        new Donner("aymen", "+A", "26/04/2021", 2),
            new Donner("aymen", "+A", "26/04/2021", 2),
            new Donner("aymen", "+A", "26/04/2021", 2),
            new Donner("aymen", "+A", "26/04/2021", 2),
            new Donner("aymen", "+A", "26/04/2021", 2),
            new Donner("aymen", "+A", "26/04/2021", 2),
            new Donner("aymen", "+A", "26/04/2021", 2)
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DonnerAdapter adapter = new DonnerAdapter(this, Donners);
        ListView list = (ListView) findViewById(R.id.donner_list);
        list.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, Form.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}