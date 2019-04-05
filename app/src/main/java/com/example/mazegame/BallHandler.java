package com.example.mazegame;

import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import java.util.ArrayList;
import java.util.Stack;


public class BallHandler implements SensorEventListener {
    private float xvel, yvel = 0.0f;
    private float xpos= 50.0f;
    private float ypos = 50.0f;
    private float xaccel, yaccel = 0.0f;
    private float xMax, yMax;
    private String TAG = "BALLHANDLING";
    private float radius;
    private Stack<Collided> collisions;

    public BallHandler(float xMax, float yMax){
        this.xMax = xMax;
        this.yMax = yMax;
        Log.d("MAX", "xMax: " + xMax + " yMax: " + yMax);
        this.radius = 0.0f;
        this.collisions = new Stack<>();

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            xaccel = -event.values[0];
            yaccel = event.values[1];
           // Log.d(TAG, "xaccel: "+xaccel+" yaccel: "+yaccel);
            updatedBall();

        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void updatedBall() {
        float oldx = xpos;
        float oldy = ypos;




            float frameLength = 0.666f;
            xvel += (xaccel * frameLength);
            yvel += (yaccel * frameLength);


            float xdisplace = (xvel / 2) * frameLength;
            float ydisplace = (yvel / 2) * frameLength;

            xpos += xdisplace;
            ypos += ydisplace;



            //Log.d(TAG, ""+collisions.isEmpty());



            if (xpos > xMax - radius) {
                xpos = xMax - radius;
                xvel = 0;
            } else if (xpos < 0) {
                xpos = 0;
                xvel = 0;
            }

            if (ypos > yMax - radius) {
                ypos = yMax - radius;
                yvel = 0;
            } else if (ypos < 0) {

                ypos = 0;
                yvel = 0;

            }
        if(!collisions.isEmpty()){

            while(!collisions.empty()){
                Collided col = collisions.pop();
                if(col.getDistancex() == 0){
                    
                    yvel = 0;
                }
                if(col.getDistancey() == 0){

                    xvel = 0;
                }
            }
        }


    }

    public float getyPos() {
        return ypos;
    }
    public float getxPos() {
        return xpos;
    }


    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius){
        this.radius = radius;
    }


    public void addCollisions(Stack<Collided> collisions) {
        this.collisions = collisions;
    }
}
