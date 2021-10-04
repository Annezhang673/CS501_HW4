package com.example.cipher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //button next to go to next activity
    public Button buttonNext;
    public EditText shift; //gets number for encryption shift from the user
    public static int key;
    public String shiftString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNext = (Button) findViewById(R.id.btnNext);
        shift = (EditText) findViewById(R.id.editTextShift);



        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                shiftString = shift.getText().toString();
                key = new Integer(shiftString).intValue();

                Intent intentEncrypt = new Intent(getApplicationContext(), Encrypt.class);
                startActivity(intentEncrypt);
            }
        });


    }

}