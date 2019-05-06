package com.example.mazegame.collisions;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

public class CollisionHandler{
    private static final String TAG = "COLLISIONS";
    public static Collided hasCollided(float ballx, float bally, float radius, Rect rectangle){
        Point rectpos = getWallUpperLeft(rectangle);
        return calculateCollided(ballx, bally, radius, rectpos, rectangle.width(), rectangle.height());


    }


    public static Collided calculateCollided(float ballx, float bally, float radius, Point rectpos, int width, int height){

        float centerx = ballx + radius;
        float centery = bally + radius;
        float nearestx = Math.max(rectpos.x, Math.min(centerx, rectpos.x + width));
        float nearesty = Math.max(rectpos.y, Math.min(centery, rectpos.y + height));
        float distancex = centerx - nearestx;
        float distancey = centery - nearesty;

        if((distancex * distancex + distancey * distancey) <= (radius * radius)){
            return new Collided(true, distancex, distancey);
        }else{
            return new Collided(false, distancex, distancey);
        }

    }

    private static Point getWallUpperLeft(Rect rectangle) {
        return new Point(rectangle.left,rectangle.top);
    }




}
