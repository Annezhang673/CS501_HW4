package com.example.hangmangame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    /* 23 animals lol */
    String[] animals = {"bear","bee","bird","cat","crab","dolphin","cow","duck","snake","frog","giraffe","horse","lion","panda","pig","tiger","turtle","whale","zebra","chicken","dog","elephant","fox"};
    /* 18 foods lol */
    String[] food = {"rice","soup","pasta","noodles","corn","bread","apple","butter","beef","pork","sushi","cake","eggs","pear","yogurt","avocado","tomato","grape"};
    /* 17 music lol */
    String[] music = {"guitar","violin","viola","cello","bass","piano","drums","clarinet","flute","trumpet","harp","jazz","indie","pop","classical","rock","edm"};

    private ImageView hangmanFrame;
    private ImageView hangmanHead;
    private ImageView hangmanTorso;
    private ImageView hangmanLeftArm;
    private ImageView hangmanRightArm;
    private ImageView hangmanLeftLeg;
    private ImageView hangmanRightLeg;
    private EditText userInput;
    private Button btnSubmit;
    private Button btnStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hangmanFrame = (ImageView) findViewById(R.id.hangmanFrame);
        hangmanHead = (ImageView) findViewById(R.id.hangmanHead);
        hangmanTorso = (ImageView) findViewById(R.id.hangmanTorso);
        hangmanLeftArm = (ImageView) findViewById(R.id.hangmanLeftArm);
        hangmanRightArm = (ImageView) findViewById(R.id.hangmanRightArm);
        hangmanLeftLeg = (ImageView) findViewById(R.id.hangmanLeftArm);
        hangmanRightLeg = (ImageView) findViewById(R.id.hangmanRightLeg);
        userInput = (EditText) findViewById(R.id.userInput);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnStartGame = (Button) findViewById(R.id.btnStartGame);

        btnStartGame.setText("Start Game");
        btnSubmit.setText("Submit");

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hangmanFrame.setVisibility(View.VISIBLE);
                userInput.setVisibility(View.VISIBLE);
                btnSubmit.setVisibility(View.VISIBLE);
            }
        });
    }

    /*
    *
    * randomly choose from three arrays a word from that array
    * dynamically set underscores to length of word
    * at same time, set up array of chars of that word
    *
    * at start game --> toast instructions
    *
    * while loop up to 6 (number of hangman parts)
    * countWrong
    * --> while (countWrong < 6) { guess as many times as user wants }
    * add user input letters into array to not repeat entries
    *
    * if countWrong == 1 -> head shows up, set to visible  (Toast wrong)
    * 2 -> torso, etc
    *
    * if user input correct -> Toast correct letter
    * update dynamically
    *
    * if all right -> Toast u r smarter than the average person
    *
    * if countWrong == 6 -> Toast game over u suck lol and end game (while loop over)
    *
    * start new game
    * set all limbs back to invisible
    * countWrong = 0
    *
    * landscape mode
    * new intent
    * start activity
    * adjust constraints and sizing
    * hint is the array name
    *
    * */
}