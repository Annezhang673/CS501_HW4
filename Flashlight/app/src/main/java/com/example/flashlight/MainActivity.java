package com.example.flashlight;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;


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
    public Switch switchBtn;
    public EditText action;

    boolean hasCameraFlash = false;
    boolean flashlOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        switchBtn = findViewById(R.id.switchBtn);

        switchBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if(hasCameraFlash){
                    if(flashlOn){
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
                }
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void flashLightOn() throws CameraAccessException {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        assert cameraManager !=null;
        String cameraID = cameraManager.getCameraIdList()[0];
        cameraManager.setTorchMode(cameraID, true);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void flashLightOff () throws CameraAccessException {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        assert cameraManager !=null;
        String cameraID = cameraManager.getCameraIdList()[0];
        cameraManager.setTorchMode(cameraID, false);
    }
}