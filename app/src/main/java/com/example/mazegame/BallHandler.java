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

    private BallVector ballVector;

    public BallHandler(float xMax, float yMax){
        this.xMax = xMax;
        this.yMax = yMax;
        this.radius = 0.0f;
        this.ballVector = new BallVector(xMax, yMax);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float xsensor = -event.values[0];
            float ysensor = event.values[1];


           if(xsensor < 2 && xsensor > -2) {
               xaccel = xsensor;
           }
           if(ysensor < 2 && ysensor > -2) {
               yaccel = ysensor;
           }
           ballVector.updateBall(xaccel, yaccel);
            //updateBall();

        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void updateBall() {
        float oldx = xpos;
        float oldy = ypos;
        
        float frameLength = 0.666f;
        xvel += (xaccel * frameLength);
        yvel += (yaccel * frameLength);

        float xdisplace = (xvel / 2) * frameLength;
        float ydisplace = (yvel / 2) * frameLength;

        xpos += xdisplace;
        ypos += ydisplace;

        checkOutofBounds();
        calculateWallCollisions(oldx, oldy);

    }

    private void calculateWallCollisions(float oldx, float oldy){
        for (int i = 0; i < maze.getMaze().getWalls().size(); i++) {
            Collided col = CollisionHandler.hasCollided(xpos, ypos, radius, maze.getRect(maze.getMaze().getWalls().get(i)));
            if(col.isHasCollided()){
                calculateCollision(col, oldx, oldy);
            }
        }
    }
    
    private void calculateCollision(Collided col, float oldx, float oldy){
        if(Math.abs(col.getDistancex()) <= (Math.abs(col.getDistancey()))){
            stopBally(oldy);
        }else if(Math.abs(col.getDistancex()) >= (Math.abs(col.getDistancey()))){
            stopBallx(oldx);
        } else if (Math.abs(col.getDistancex()) == (Math.abs(col.getDistancey()))) {
            stopBally(oldy);
            stopBallx(oldx);
        }else   if (Math.abs(col.getDistancey()) < Math.abs(radius) && Math.abs(col.getDistancex()) == 0) {
            stopBally(oldy);
        }else if (Math.abs(col.getDistancex()) < Math.abs(radius) && Math.abs(col.getDistancey()) == 0) {
            stopBallx(oldx);

        } else {
            stopBally(oldy);
            stopBallx(oldx);
        }
    }
    
    private void stopBallx(float oldx){
        xpos = oldx;
        xvel = 0;
    }
    
    private void stopBally(float oldy){
        ypos = oldy;
        yvel = 0;

    }
    
    

    private void checkOutofBounds(){
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
        return ballVector.getYpos();
    }
    public float getxPos() {
        return ballVector.getXpos();
    }

    public void setMaze(MazeCanvas maze){ballVector.setMaze(maze);}
    
    public float getRadius(){return ballVector.getRadius();}
    public void setRadius(float radius){
        ballVector.setRadius(radius);
    }

    public void setxPos(float xpos){ballVector.setXpos(xpos);}
    public void setyPos(float ypos){ballVector.setYpos(ypos);}


}
