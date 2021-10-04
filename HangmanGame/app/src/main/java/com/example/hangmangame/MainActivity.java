package com.example.hangmangame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
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

    boolean game = false;

    private ImageView hangmanFrame;
    private ImageView hangmanHead;
    private ImageView hangmanTorso;
    private ImageView hangmanLeftArm;
    private ImageView hangmanRightArm;
    private ImageView hangmanLeftLeg;
    private ImageView hangmanRightLeg;
    private ImageView[] hangmanBody;
    int countWrong = 0; // number wrong
    char[] randomWordArray = {}; // word selected put into char array
    String wordSelectedUnderscores = ""; // displayed string of underscores
    ArrayList<Character> wordSelectedUnderscoresArr = new ArrayList<Character>(); // displayed string of underscores arraylist

    private TextView wordSelected; // word selected textview
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
    int btnQNum = 0;
    int btnWNum = 1;
    int btnENum = 2;
    int btnRNum = 3;
    int btnTNum = 4;
    int btnYNum = 5;
    int btnUNum = 6;
    int btnINum = 7;
    int btnONum = 8;
    int btnPNum = 9;
    int btnANum = 10;
    int btnSNum = 11;
    int btnDNum = 12;
    int btnFNum = 13;
    int btnGNum = 14;
    int btnHNum = 15;
    int btnJNum = 16;
    int btnKNum = 17;
    int btnLNum = 18;
    int btnZNum = 19;
    int btnXNum = 20;
    int btnCNum = 21;
    int btnVNum = 22;
    int btnBNum = 23;
    int btnNNum = 24;
    int btnMNum = 25;
    private Button[] keyboardButtons;
    int[] keyboardButtonsUsed = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; // list of btn visibility (0 = visible, 4 = invisible)

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("countWrong", countWrong);
        outState.putCharArray("randomWordArray", randomWordArray);
        outState.putString("wordSelected", wordSelected.getText().toString());
        outState.putIntArray("keyboardButtonsUsed", keyboardButtonsUsed);
        outState.putBoolean("game", game);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int savedCountWrong = savedInstanceState.getInt("countWrong");
        countWrong = savedCountWrong;
        hangmanHead = (ImageView) findViewById(R.id.hangmanHead);
        hangmanTorso = (ImageView) findViewById(R.id.hangmanTorso);
        hangmanLeftArm = (ImageView) findViewById(R.id.hangmanLeftArm);
        hangmanRightArm = (ImageView) findViewById(R.id.hangmanRightArm);
        hangmanLeftLeg = (ImageView) findViewById(R.id.hangmanLeftLeg);
        hangmanRightLeg = (ImageView) findViewById(R.id.hangmanRightLeg);
        hangmanBody = new ImageView[]{hangmanLeftLeg, hangmanRightLeg, hangmanLeftArm, hangmanRightArm, hangmanTorso, hangmanHead};
        for (int iter = 0; iter < countWrong; iter++) {
            hangmanBody[iter].setVisibility(View.VISIBLE);
        }

        char[] savedRandomWordArray = savedInstanceState.getCharArray("randomWordArray");
        randomWordArray = savedRandomWordArray;

        String savedWordSelected = savedInstanceState.getString("wordSelected");
        wordSelected.setText(savedWordSelected);
        for (char c : savedWordSelected.toCharArray()) {
            wordSelectedUnderscoresArr.add(c);
        }

        int[] savedKeyboardButtonsUsed = savedInstanceState.getIntArray("keyboardButtonsUsed");
        keyboardButtons = new Button[]{btnQ, btnW, btnE, btnR, btnT, btnY, btnU, btnI, btnO, btnP, btnA, btnS, btnD, btnF, btnG, btnH, btnJ, btnK, btnL, btnZ, btnX, btnC, btnV, btnB, btnN, btnM};
        for (int iter = 0; iter < savedKeyboardButtonsUsed.length; iter++) {
            keyboardButtons[iter].setVisibility(savedKeyboardButtonsUsed[iter]);
        }

        boolean savedGame = savedInstanceState.getBoolean("game");
        game = savedGame;

        if (game) {
            hangmanFrame.setVisibility(View.VISIBLE);
            keyboard.setVisibility(View.VISIBLE);
        }
    }

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
                game = true;
                hangmanFrame.setVisibility(View.VISIBLE);

                // for restarting
                countWrong = 0;
                wordSelectedUnderscores = "";
                wordSelectedUnderscoresArr.clear();
                hangmanBody = new ImageView[]{hangmanLeftLeg, hangmanRightLeg, hangmanLeftArm, hangmanRightArm, hangmanTorso, hangmanHead};
                for (int iter = 0; iter < hangmanBody.length; iter++) {
                    hangmanBody[iter].setVisibility(View.INVISIBLE);
                }
                keyboardButtons = new Button[]{btnQ, btnW, btnE, btnR, btnT, btnY, btnU, btnI, btnO, btnP, btnA, btnS, btnD, btnF, btnG, btnH, btnJ, btnK, btnL, btnZ, btnX, btnC, btnV, btnB, btnN, btnM};
                for (int iter = 0; iter < keyboardButtons.length; iter++) {
                    keyboardButtons[iter].setVisibility(View.VISIBLE);
                }

                // randomly selects category
                int randomArrChooser = new Random().nextInt(allWords.length);
                String[] randomArrSelected = allWords[randomArrChooser];
                // randomly selects word from category
                int randomKeyChooser = new Random().nextInt(randomArrSelected.length);
                String randomKeySelected = randomArrSelected[randomKeyChooser];
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
                btnQ.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnQNum] = 4;
            }
        });
        btnW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('w');
                btnW.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnWNum] = 4;
            }
        });
        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('e');
                btnE.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnENum] = 4;
            }
        });
        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('r');
                btnR.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnRNum] = 4;
            }
        });
        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('t');
                btnT.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnTNum] = 4;
            }
        });
        btnY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('y');
                btnY.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnYNum] = 4;
            }
        });
        btnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('u');
                btnU.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnUNum] = 4;
            }
        });
        btnI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('i');
                btnI.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnINum] = 4;
            }
        });
        btnO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('o');
                btnO.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnONum] = 4;
            }
        });
        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('p');
                btnP.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnPNum] = 4;
            }
        });
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('a');
                btnA.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnANum] = 4;
            }
        });
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('s');
                btnS.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnSNum] = 4;
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('d');
                btnD.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnDNum] = 4;
            }
        });
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('f');
                btnF.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnFNum] = 4;
            }
        });
        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('g');
                btnG.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnGNum] = 4;
            }
        });
        btnH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('h');
                btnH.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnHNum] = 4;
            }
        });
        btnJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('j');
                btnJ.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnJNum] = 4;
            }
        });
        btnK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('k');
                btnK.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnKNum] = 4;
            }
        });
        btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('l');
                btnL.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnLNum] = 4;
            }
        });
        btnZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('z');
                btnZ.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnZNum] = 4;
            }
        });
        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('x');
                btnX.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnXNum] = 4;
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('c');
                btnC.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnCNum] = 4;
            }
        });
        btnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('v');
                btnV.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnVNum] = 4;
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('b');
                btnB.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnBNum] = 4;
            }
        });
        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('n');
                btnN.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnNNum] = 4;
            }
        });
        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterChecker('m');
                btnM.setVisibility(View.INVISIBLE);
                keyboardButtonsUsed[btnMNum] = 4;
            }
        });
    }

    /* takes button onclick to update game counters and UI */
    private void letterChecker(char fromButton) {
        // linear search to check if input letter is in the word
        boolean check = false;
        for (char c : randomWordArray) {
            if (c == fromButton) {
                check = true;
            }
        }
        List<Integer> indexList = new ArrayList<Integer>();

        if (!check) {
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