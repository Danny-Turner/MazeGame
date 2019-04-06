package com.example.mazegame.collisions;

public class Collided {
    private boolean hasCollided;
    private float distancex, distancey, nearestx, nearesty;

    public Collided(boolean hasCollided, float distancex, float distancey, float nearestx, float nearesty){
        this.hasCollided = hasCollided;
        this.distancex = distancex;
        this.distancey = distancey;
        this.nearestx = nearestx;
        this.nearesty = nearesty;



    }

    public boolean isHasCollided() {
        return hasCollided;
    }

    public float getDistancex() {
        return distancex;
    }

    public float getDistancey() {
        return distancey;
    }

    public float getNearestx() {
        return nearestx;
    }

    public float getNearesty() {
        return nearesty;
    }
}
