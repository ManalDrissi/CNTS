package com.example.cnts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Form extends AppCompatActivity implements View.OnClickListener {
    private Button[] btn = new Button[4];
    private Button[] btn_type = new Button[2];

    private Button btn_unfocus;
    private Button btn_type_unfocus;

    private int[] btn_id = {R.id.typeA, R.id.typeB, R.id.typeAB, R.id.typeO};
    private int[] btn_id_type = {R.id.positive, R.id.negative};

    String name , phone , date,type,value;
    TextInputLayout nameInput;
    TextInputLayout dateInput;
    TextInputLayout phoneInput;

    Button submitButton,cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        nameInput = (TextInputLayout)findViewById(R.id.Name);
        phoneInput = (TextInputLayout)findViewById(R.id.Phone);
        dateInput = (TextInputLayout)findViewById(R.id.BirthDate);

        submitButton = (Button)findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V) {
                name = nameInput.getEditText().getText().toString();
                phone = phoneInput.getEditText().getText().toString();
                date = dateInput.getEditText().getText().toString();
            }
        });
        cancelButton = (Button)findViewById(R.id.cancel);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V) {
                final Intent intent = new Intent(Form.this, MainActivity.class);
                startActivity(intent);
            }
        });
        for(int i = 0; i < btn.length; i++){
            btn[i] = (Button) findViewById(btn_id[i]);
            btn[i].setTextColor(Color.parseColor("#434343"));
            btn[i].setBackgroundColor(Color.parseColor("#FFFCFC"));
            btn[i].setOnClickListener(this::onClick);
        }

        for(int i = 0; i < btn_type.length; i++){
            btn_type[i] = (Button) findViewById(btn_id_type[i]);
            btn_type[i].setTextColor(Color.parseColor("#434343"));
            btn_type[i].setBackgroundColor(Color.parseColor("#FFFCFC"));
            btn_type[i].setOnClickListener(this::onClickType);
        }

        btn_unfocus = btn[0];
        btn_type_unfocus = btn_type[0];
        setFocus(btn_unfocus, btn[0]);
        setTypeFocus(btn_type_unfocus, btn_type[0]);
    }


    @Override
    public void onClick(View v) {
        //setForcus(btn_unfocus, (Button) findViewById(v.getId()));
        //Or use switch
        switch (v.getId()){
            case R.id.typeA :
                setFocus(btn_unfocus, btn[0]);
                break;

            case R.id.typeB :
                setFocus(btn_unfocus, btn[1]);
                break;

            case R.id.typeAB :
                setFocus(btn_unfocus, btn[2]);
                break;

            case R.id.typeO :
                setFocus(btn_unfocus, btn[3]);
                break;
        }
    }

    public void onClickType(View v) {
        switch (v.getId()){
            case R.id.positive :
                setTypeFocus(btn_type_unfocus, btn_type[0]);
                break;

            case R.id.negative :
                setTypeFocus(btn_type_unfocus, btn_type[1]);
                break;
        }
    }



    private void setFocus(Button btn_unfocus, Button btn_focus){
        btn_unfocus.setTextColor(Color.parseColor("#434343"));
        btn_unfocus.setBackgroundColor(Color.parseColor("#FFFCFC"));
        btn_focus.setTextColor(Color.parseColor("#FFFFFF"));
        btn_focus.setBackgroundColor(Color.parseColor("#D80032"));
        this.btn_unfocus = btn_focus;
        this.type = btn_focus.getText().toString();
    }
    private void setTypeFocus(Button btn_type_unfocus, Button btn_type_focus){
        btn_type_unfocus.setTextColor(Color.parseColor("#434343"));
        btn_type_unfocus.setBackgroundColor(Color.parseColor("#FFFCFC"));
        btn_type_focus.setTextColor(Color.parseColor("#FFFFFF"));
        btn_type_focus.setBackgroundColor(Color.parseColor("#D80032"));
        this.btn_type_unfocus = btn_type_focus;
        this.value = btn_type_focus.getText().toString();
    }

    private void showToast(String text) {
        Toast.makeText(Form.this,text,Toast.LENGTH_LONG).show();
    }
}