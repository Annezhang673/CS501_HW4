package com.example.w4_p4_pulling_it_all_together;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MyGestureListener mgListener;
    private GestureDetector mDetector;
//    private final static String TAG = "MyGesture";

//    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mgListener = new MyGestureListener();
        mDetector = new GestureDetector(this, mgListener);
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
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float vx, float vy) {
            if (vx > 500){
                Intent GotoActivity1_E = new Intent(getApplicationContext(), MainActivity1_E.class);
                startActivity(GotoActivity1_E);
                return false;
            }
            if (vx < -500){
                Intent GotoActivity2_W = new Intent(getApplicationContext(), MainActivity2_W.class);
                startActivity(GotoActivity2_W);
                return false;
            }
            if (vy < -500){
                Intent GotoActivity3_N = new Intent(getApplicationContext(), MainActivity3_N.class);
                startActivity(GotoActivity3_N);
                return false;
            }
            if (vy > 500){
                Intent GotoActivity4_S = new Intent(getApplicationContext(), MainActivity4_S.class);
                startActivity(GotoActivity4_S);
                return false;
            }
            return false;
        }
    }
}