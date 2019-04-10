package com.example.mazegame.collisions;

public class Collided {
    private boolean hasCollided;
    private float distancex, distancey;

    public Collided(boolean hasCollided, float distancex, float distancey){
        this.hasCollided = hasCollided;
        this.distancex = distancex;
        this.distancey = distancey;
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

}
