package com.example.flashlight;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;


//Use any layout you like, but make it look similar to the diagram below.
//        2. Use a simple toggle switch to turn the flashlight on/off.
//        3. Make it so entering text into the Action Text Box will also affect the flashlight. ‘ON’ turns on
//        the flashlight, ‘OFF’ turns off the flashlight.
//        a. Ensure that entering ‘ON’ or ‘OFF’ appropriately flips the toggle switch, if needed.
//        4. Modify your code to dynamically render the toggle button at startup.
//        5. Use a fling, fling up, turns on the flashlight, fling down, turns off the flashlight. Ensure the
//        fling is "significant" and not an accident as a result of the user lifting their finger too quickly
//        off of the screen.
//        a. Again ensure that the toggle switch, toggles to the appropriate setting.
//        6. Ensure you handle exceptions appropriately, for example flash light not available, etc.

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout fullLayout;
    private Switch switchBtn;
    private EditText action;
    private Button submitAction;
    private ImageView swipeView;

    private MyGestureListener swipeListener;
    private GestureDetector swipeDetector;

    boolean hasCameraFlash = false;
    boolean flashlOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullLayout = (ConstraintLayout) findViewById(R.id.fullLayout);
        fullLayout.setBackgroundColor(Color.parseColor("#7393B3"));
        hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        System.out.println(hasCameraFlash);
        switchBtn = findViewById(R.id.switchBtn);
        action = findViewById(R.id.action);
        submitAction = findViewById(R.id.submitAction);
        submitAction.setText("SUBMIT");
        swipeView = findViewById(R.id.swipeView);

        // turning image to invisible
        swipeView.setImageResource(android.R.color.transparent);
        swipeListener = new MyGestureListener();
        swipeDetector = new GestureDetector(this, swipeListener);

        /* using switch toggle to turn on flash */
        switchBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (hasCameraFlash) {
                    if (flashlOn) {
                        flashlOn = false;
                        try {
                            flashLightOff();
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        flashlOn = true;
                        try {
                            flashLightOn();
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Unfortunately, your device does not have Flash LED capabilities.", Toast.LENGTH_LONG).show();
                }
            }
        });

        /* entering "on" or "off" in editText to turn on/off flash */
        submitAction.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                final String userInput = action.getText().toString();
                if (userInput.toLowerCase().equals("on")) {
                    if (hasCameraFlash) {
                        if (!flashlOn) {
                            flashlOn = true;
                            fullLayout.setBackgroundColor(Color.parseColor("#FFFF00"));
                            try {
                                flashLightOn();
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Flashlight is already on.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Unfortunately, your device does not have Flash LED capabilities.", Toast.LENGTH_LONG).show();
                    }
                } else if (userInput.toLowerCase().equals("off")) {
                    if (hasCameraFlash) {
                        if (flashlOn) {
                            flashlOn = false;
                            fullLayout.setBackgroundColor(Color.parseColor("#7393B3"));
                            try {
                                flashLightOff();
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Flashlight is already off.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Unfortunately, your device does not have Flash LED capabilities.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid input. Enter 'on' or 'off'.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    /* turning flashlight on changes background to yellow */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void flashLightOn() throws CameraAccessException {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        assert cameraManager !=null;
        String cameraID = cameraManager.getCameraIdList()[0];
        cameraManager.setTorchMode(cameraID, true);

        fullLayout.setBackgroundColor(Color.parseColor("#FFFF00"));
    }

    /* turning flashlight off changes color back to gray blue */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void flashLightOff () throws CameraAccessException {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        assert cameraManager !=null;
        String cameraID = cameraManager.getCameraIdList()[0];
        cameraManager.setTorchMode(cameraID, false);

        fullLayout.setBackgroundColor(Color.parseColor("#7393B3"));
    }



    /* gesture detector class */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return swipeDetector.onTouchEvent(event);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onSwipeUp(){
        if (hasCameraFlash) {
            if (!flashlOn) {
                flashlOn = true;
                fullLayout.setBackgroundColor(Color.parseColor("#FFFF00"));
                try {
                    flashLightOn();
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Flashlight is already on.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Unfortunately, your device does not have Flash LED capabilities.", Toast.LENGTH_LONG).show();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onSwipeDown(){
        if (hasCameraFlash) {
            if (flashlOn) {
                flashlOn = false;
                fullLayout.setBackgroundColor(Color.parseColor("#7393B3"));
                try {
                    flashLightOff();
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Flashlight is already off.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Unfortunately, your device does not have Flash LED capabilities.", Toast.LENGTH_LONG).show();
        }
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

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (velocityY < -500) {
                onSwipeUp();
                return false;
            } else if (velocityY > 500) {
                onSwipeDown();
                return false;
            }
            return false;
        }
    }
}