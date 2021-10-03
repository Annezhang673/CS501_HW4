package com.example.hangmangame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /* 23 animals lol */
    static final String[] animals = {"bear","bee","bird","cat","crab","dolphin","cow","duck","snake","frog","giraffe","horse","lion","panda","pig","tiger","turtle","whale","zebra","chicken","dog","elephant","fox"};
    /* 18 foods lol */
    static final String[] food = {"rice","soup","pasta","noodles","corn","bread","apple","butter","beef","pork","sushi","cake","eggs","pear","yogurt","avocado","tomato","grape"};
    /* 17 music lol */
    static final String[] music = {"guitar","violin","viola","cello","bass","piano","drums","clarinet","flute","trumpet","harp","jazz","indie","pop","classical","rock","edm"};
    /* array of arrays */
    static final String[][] allWords = {animals, food, music};

    private ImageView hangmanFrame;
    private ImageView hangmanHead;
    private ImageView hangmanTorso;
    private ImageView hangmanLeftArm;
    private ImageView hangmanRightArm;
    private ImageView hangmanLeftLeg;
    private ImageView hangmanRightLeg;
    private ImageView[] hangmanBody;
    int countWrong = 0;
    char[] randomWordArray = {};
    String wordSelectedUnderscores = "";
    List<Character> wordSelectedUnderscoresArr = new ArrayList<Character>();
    private TextView theword;

    private TextView wordSelected;
    private Button btnStartGame;

    private TableLayout keyboard;
    private TableRow qwertyuiop;
    private TableRow asdfghjkl;
    private TableRow zxcvbnm;
    private Button btnQ;
    private Button btnW;
    private Button btnE;
    private Button btnR;
    private Button btnT;
    private Button btnY;
    private Button btnU;
    private Button btnI;
    private Button btnO;
    private Button btnP;
    private Button btnA;
    private Button btnS;
    private Button btnD;
    private Button btnF;
    private Button btnG;
    private Button btnH;
    private Button btnJ;
    private Button btnK;
    private Button btnL;
    private Button btnZ;
    private Button btnX;
    private Button btnC;
    private Button btnV;
    private Button btnB;
    private Button btnN;
    private Button btnM;
    final Button[] keyboardButtons = {btnQ, btnW, btnE, btnR, btnT, btnY, btnU, btnI, btnO, btnP, btnA, btnS, btnD, btnF, btnG, btnH, btnJ, btnK, btnL, btnZ, btnX, btnC, btnV, btnB, btnN, btnM};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hangmanFrame = (ImageView) findViewById(R.id.hangmanFrame);
        hangmanHead = (ImageView) findViewById(R.id.hangmanHead);
        hangmanTorso = (ImageView) findViewById(R.id.hangmanTorso);
        hangmanLeftArm = (ImageView) findViewById(R.id.hangmanLeftArm);
        hangmanRightArm = (ImageView) findViewById(R.id.hangmanRightArm);
        hangmanLeftLeg = (ImageView) findViewById(R.id.hangmanLeftLeg);
        hangmanRightLeg = (ImageView) findViewById(R.id.hangmanRightLeg);
        wordSelected = (TextView) findViewById(R.id.wordSelected);
        btnStartGame = (Button) findViewById(R.id.btnStartGame);

        theword = (TextView) findViewById(R.id.theword);

        keyboard = (TableLayout) findViewById(R.id.keyboard);
        qwertyuiop = (TableRow) findViewById(R.id.qwertyuiop);
        asdfghjkl = (TableRow) findViewById(R.id.asdfghjkl);
        zxcvbnm = (TableRow) findViewById(R.id.zxcvbnm);

        btnQ = (Button) findViewById(R.id.btnQ);
        btnW = (Button) findViewById(R.id.btnW);
        btnE = (Button) findViewById(R.id.btnE);
        btnR = (Button) findViewById(R.id.btnR);
        btnT = (Button) findViewById(R.id.btnT);
        btnY = (Button) findViewById(R.id.btnY);
        btnU = (Button) findViewById(R.id.btnU);
        btnI = (Button) findViewById(R.id.btnI);
        btnO = (Button) findViewById(R.id.btnO);
        btnP = (Button) findViewById(R.id.btnP);
        btnA = (Button) findViewById(R.id.btnA);
        btnS = (Button) findViewById(R.id.btnS);
        btnD = (Button) findViewById(R.id.btnD);
        btnF = (Button) findViewById(R.id.btnF);
        btnG = (Button) findViewById(R.id.btnG);
        btnH = (Button) findViewById(R.id.btnH);
        btnJ = (Button) findViewById(R.id.btnJ);
        btnK = (Button) findViewById(R.id.btnK);
        btnL = (Button) findViewById(R.id.btnL);
        btnZ = (Button) findViewById(R.id.btnZ);
        btnX = (Button) findViewById(R.id.btnX);
        btnC = (Button) findViewById(R.id.btnC);
        btnV = (Button) findViewById(R.id.btnV);
        btnB = (Button) findViewById(R.id.btnB);
        btnN = (Button) findViewById(R.id.btnN);
        btnM = (Button) findViewById(R.id.btnM);

        btnStartGame.setText("Start Game");
        /* START GAME */
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hangmanFrame.setVisibility(View.VISIBLE);

                // for restarting
                countWrong = 0;
                wordSelectedUnderscores = "";
                wordSelectedUnderscoresArr.clear();
                hangmanBody = new ImageView[]{hangmanLeftLeg, hangmanRightLeg, hangmanLeftArm, hangmanRightArm, hangmanTorso, hangmanHead};
                for (int iter = 0; iter < hangmanBody.length; iter++) {
                    hangmanBody[iter].setVisibility(View.INVISIBLE);
                }

                // randomly selects category
                int randomArrChooser = new Random().nextInt(allWords.length);
                String[] randomArrSelected = allWords[randomArrChooser];
                // randomly selects word from category
                int randomKeyChooser = new Random().nextInt(randomArrSelected.length);
                String randomKeySelected = randomArrSelected[randomKeyChooser];
                theword.setText(randomKeySelected);
                // turning word into array of chars
                randomWordArray = randomKeySelected.toCharArray();
                System.out.println(randomWordArray);
                // building the underscores
                for (int iter = 0; iter < randomWordArray.length; iter++) {
                    wordSelectedUnderscores += "_";
                    wordSelectedUnderscoresArr.add('_');
                }
                // displaying the underscores of the word
                wordSelected.setText(wordSelectedUnderscores);

                // start up the keyboard
                keyboard.setVisibility(View.VISIBLE);
            }
        });

        btnQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('q');
            }
        });
        btnW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('w');
            }
        });
        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('e');
            }
        });
        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('r');
            }
        });
        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('t');
            }
        });
        btnY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('y');
            }
        });
        btnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('u');
            }
        });
        btnI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('i');
            }
        });
        btnO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('o');
            }
        });
        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('p');
            }
        });
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('a');
            }
        });
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('s');
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('d');
            }
        });
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('f');
            }
        });
        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('g');
            }
        });
        btnH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('h');
            }
        });
        btnJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('j');
            }
        });
        btnK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('k');
            }
        });
        btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('l');
            }
        });
        btnZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('z');
            }
        });
        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('x');
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('c');
            }
        });
        btnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('v');
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('b');
            }
        });
        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('n');
            }
        });
        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('m');
            }
        });
    }

    /* takes button onclick to update game counters and UI */
    private void letterChecker(char fromButton) {
        // binary search to check if input letter is in the word
        int check = Arrays.binarySearch(randomWordArray, fromButton);
        List<Integer> indexList = new ArrayList<Integer>();

        if (check < 0) {
            // negative value means not in the word -> wrong -> set next body part visible
            hangmanBody[countWrong].setVisibility(View.VISIBLE);
            countWrong++;

            // 6 wrongs = end game -> Toast & turn off keyboard
            if (countWrong == hangmanBody.length) {
                keyboard.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "OH NO! You killed Hangman! Game Over. Try again :)", Toast.LENGTH_LONG).show();
            }

        } else {
            // else user got it right -> update underscore(s)

            // getting the array indexes of letter instance in word
            for (int iter = 0; iter < randomWordArray.length; iter++) {
                if (randomWordArray[iter] == fromButton) {
                    indexList.add(iter);
                }
            }

            // updating the array of underscores
            for (int iter = 0; iter < indexList.size(); iter++) {
                wordSelectedUnderscoresArr.set(indexList.get(iter), fromButton);
            }
            StringBuilder sb = new StringBuilder();
            for (Character ch : wordSelectedUnderscoresArr) {
                sb.append(ch);
            }
            wordSelected.setText(sb.toString());

            // whole word completed
            boolean contains = false;
            for (char c : wordSelectedUnderscoresArr) {
                if (c == '_') {
                    contains = true;
                    break;
                }
            }
            System.out.println(contains);
            if (contains == false) {
                keyboard.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "CONGRATS! You won! Play again :)", Toast.LENGTH_LONG).show();
            }
        }
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