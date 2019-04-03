package com.example.mazegame;

import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;



public class BallHandler implements SensorEventListener {
    private float xvel, yvel = 0.0f;
    private float xpos, ypos = 300.0f;
    private float xaccel, yaccel = 0.0f;
    private float xMax, yMax;
    private String TAG = "BALLHANDLING";


    public BallHandler(float xMax, float yMax){
        this.xMax = xMax;
        this.yMax = yMax;

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            xaccel = -event.values[0];
            yaccel = event.values[1];
            Log.d(TAG, "xaccel: "+xaccel+" yaccel: "+yaccel);
            updatedBall();

        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void updatedBall() {
        //TODO: when the ball hits a wall these numbers keep getting larger
        float frameLength = 0.666f;
        xvel += (xaccel * frameLength);
        yvel += (yaccel * frameLength);



        float xdisplace = (xvel/2) * frameLength;
        float ydisplace = (yvel/2) * frameLength;

        xpos += xdisplace;
        ypos += ydisplace;

        Log.d(TAG, "xpos: " + xpos + " ypos: " + ypos);

        if (xpos > xMax) {
            xpos = xMax;
            xvel = 0;
        } else if (xpos < 0) {
            xpos = 0;
            xvel = 0;
        }

        if (ypos > yMax) {
            ypos = yMax;
            yvel = 0;
        } else if (ypos < 0) {
            ypos = 0;
            yvel = 0;

        }
    }

    public float getyPos() {
        return ypos;
    }
    public float getxPos() {
        return xpos;
    }


}
