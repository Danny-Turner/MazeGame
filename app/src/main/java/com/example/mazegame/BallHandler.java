package com.example.mazegame;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import com.example.mazegame.collisions.Collided;
import com.example.mazegame.collisions.CollisionHandler;


public class BallHandler implements SensorEventListener {
    private float xvel, yvel = 0.0f;
    private float xpos= 50.0f;
    private float ypos = 30.0f;
    private float xaccel, yaccel = 0.0f;
    private float xMax, yMax;
    private String TAG = "BALLHANDLING";
    private float radius;
    private MazeCanvas maze;


    public BallHandler(float xMax, float yMax){
        this.xMax = xMax;
        this.yMax = yMax;
        Log.d("MAX", "xMax: " + xMax + " yMax: " + yMax);
        this.radius = 0.0f;


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

        checkOutofBounds();
        calcuateWallCollisions(oldx, oldy);








    }

    public void calcuateWallCollisions(float oldx, float oldy){
        for (int i = 0; i < maze.getMaze().getWalls().size(); i++) {
            Collided col = CollisionHandler.hascollided(xpos, ypos, radius, maze.getRect(maze.getMaze().getWalls().get(i)));
            if(col.isHasCollided()){
                if(Math.abs(col.getDistancex()) < (Math.abs(col.getDistancey()))){
                    ypos = oldy;
                    yvel = 0;
                }else if(Math.abs(col.getDistancex()) > (Math.abs(col.getDistancey()))){
                    xpos = oldx;
                    xvel = 0;
                } else if (Math.abs(col.getDistancey()) < Math.abs(radius) && Math.abs(col.getDistancex()) == 0) {
                    ypos = oldy;
                    yvel = 0;

                }else if (Math.abs(col.getDistancex()) < Math.abs(radius) && Math.abs(col.getDistancey()) == 0) {
                    xpos = oldx;
                    xvel = 0;

                }
            }
        }
    }

    public void checkOutofBounds(){
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
    }

    public float getyPos() {
        return ypos;
    }
    public float getxPos() {
        return xpos;
    }


    public void setMaze(MazeCanvas maze){this.maze = maze;}


    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius){
        this.radius = radius;
    }


}
