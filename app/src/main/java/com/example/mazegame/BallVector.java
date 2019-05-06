package com.example.mazegame;

import com.example.mazegame.collisions.Collided;
import com.example.mazegame.collisions.CollisionHandler;

public class BallVector {
    private float xvel, yvel = 0.0f;
    private float xpos= 50.0f;
    private float ypos = 30.0f;
    private float xMax, yMax;
    private String TAG = "BALLHANDLING";
    private float radius;
    private MazeCanvas maze;

    public BallVector(float xMax, float yMax){
        this.xMax = xMax;
        this.yMax = yMax;
        this.radius = 0.0f;
    }

    public void setMaze(MazeCanvas maze){
        this.maze = maze;
    }
    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius(){
        return this.radius;
    }

    public void setXpos(float xpos){
        this.xpos = xpos;
    }

    public float getXpos() {
        return xpos;
    }

    public void setYpos(float ypos){
        this.ypos = ypos;
    }

    public float getYpos() {
        return ypos;
    }

    public void updateBall(float xaccel, float yaccel) {
        float oldx = xpos;
        float oldy = ypos;

        Point displace = calculateDisplacement(xaccel, yaccel);

        xpos += displace.getX();
        ypos += displace.getY();

        checkOutofBounds();
        calculateWallCollisions(oldx, oldy);

    }


    public Point calculateDisplacement(float xaccel, float yaccel){
        float frameLength = 0.666f;
        xvel += (xaccel * frameLength);
        yvel += (yaccel * frameLength);

        float xdisplace = (xvel / 2) * frameLength;
        float ydisplace = (yvel / 2) * frameLength;
        return new Point(xdisplace, ydisplace);
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


}
