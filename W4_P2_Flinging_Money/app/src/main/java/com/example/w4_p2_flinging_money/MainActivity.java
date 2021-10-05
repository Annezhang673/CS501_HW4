package com.example.w4_p2_flinging_money;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{
    private EditText euro;
    private TextView USA;
    private TextView KOR;
    private TextView JAP;
    private TextView AUD;
    private MyGestureListener mgListener;
    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        euro = (EditText) findViewById(R.id.euro);
        USA = (TextView) findViewById(R.id.USA);
        KOR = (TextView) findViewById(R.id.KOR);
        JAP = (TextView) findViewById(R.id.JAP);
        AUD = (TextView) findViewById(R.id.AUD);

        mgListener = new MyGestureListener();
        mDetector = new GestureDetector(this, mgListener);
        euro.addTextChangedListener(euroTextWatcher);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    private class MyGestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            double num;
            if ((euro.getText().toString().isEmpty())){
                num = 0;
            } else {
                num = Math.round(Double.parseDouble(euro.getText().toString()));
            }

            String numText;

            // if scrolled up, increment 5 cents
            if (distanceY > 0){
                num += 0.05;
                numText = String.valueOf(num);
                euro.setText(numText);
                setCurrency();
            }
            // scrolled down, decrease 5 cents
            if (distanceY < 0) {
                num -= 0.05;
                numText = String.valueOf(num);
                euro.setText(numText);
                setCurrency();
            }

            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float vx, float vy) {
            double num;
            if ((euro.getText().toString().isEmpty())){
                num = 0;
            } else {
                num = Math.round(Double.parseDouble(euro.getText().toString()));
            }

            String numText;
            // fling up, make 1 dollar increment
            if (vy > 500){
                num += 1.0;
                numText = String.valueOf(num);
                euro.setText(numText);
                setCurrency();
            }

            // fling down, make 1 dollar decrement
            if (vy < -500){
                num -= 1.0;
                numText = String.valueOf(num);
                euro.setText(numText);
                setCurrency();
            }

            return false;
        }
    }

    private final TextWatcher euroTextWatcher= new TextWatcher(){
        boolean ignore = false;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (ignore) {
                return;
            }

            // prevent infinite loop
            ignore = true;
            setCurrency();

            ignore = false; // release, so the TextWatcher start to listen again.
        }
    };

    // this will set all of the correct currency values for the 4 other countries
    private void setCurrency(){
        double euroValue;
        double usaValue;
        double korValue;
        double japValue;
        double audValue;

        if ((euro.getText().toString().isEmpty())){
            euroValue = 0;
        } else {
            euroValue = Math.round(Double.parseDouble(euro.getText().toString()));
        }

        // set US value
        usaValue = euroValue * 1.1619171;
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        USA.setText(defaultFormat.format(usaValue));

        // set WON value
        korValue = euroValue * 1376.379;
        Locale korea = new Locale("ko", "KR");
        NumberFormat koreaFormat = NumberFormat.getCurrencyInstance(korea);
        KOR.setText(koreaFormat.format(korValue));

        // set YEN value
        japValue = euroValue * 128.89553;
        Locale japan = new Locale("ja", "JP");
        NumberFormat japanFormat = NumberFormat.getCurrencyInstance(japan);
        JAP.setText(japanFormat.format(japValue));

        // set AUD value
        audValue = euroValue * 1.5942122;
        Locale aus = new Locale("en", "AU");
        NumberFormat ausFormat = NumberFormat.getCurrencyInstance(aus);
        AUD.setText(ausFormat.format(audValue));

    }

}