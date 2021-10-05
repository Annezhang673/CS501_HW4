package com.example.cipher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Encrypt extends AppCompatActivity {

    private static String alphabetString = "abcdefghijklmnopqrstuvwxyz";
    public Button buttonEncrypt;
    public EditText input;
    public TextView output;
    public int shiftKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);

        buttonEncrypt = (Button) findViewById(R.id.btnEncrypt);
        shiftKey = MainActivity.key;

        input = (EditText) findViewById(R.id.userInput);
        output = (TextView) findViewById(R.id.result);

        buttonEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(encrypt(input.getText().toString(), shiftKey));
            }
        });

    }

    public static String encrypt(String inp, int key1){

        String cipherTxt = "";
        int length = inp.length();
        for(int i = 0; i < length; i++){
            if (inp.charAt(i) == ' '){
                cipherTxt += " ";
            }
            else{
                int charPosition = alphabetString.indexOf(inp.charAt(i));
                int updposition = (key1 + charPosition)%26;
                char newval = alphabetString.charAt(updposition);
                cipherTxt += newval;
            }
        }
        return cipherTxt;
    }




}