package com.example.mazegame;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

public class CollisionHandler {

    public static boolean hascollided(BallHandler ball, Rect rectangle){
        Point rectpos = getWallUpperLeft(rectangle);

        float nearestx = Math.max(rectpos.x, Math.min(ball.getxPos(), rectpos.x + rectangle.width()));
        float nearesty = Math.max(rectpos.y, Math.min(ball.getyPos(), rectpos.y + rectangle.height()));
        float distancex = ball.getxPos() - nearestx;
        float distancey = ball.getyPos() - nearesty;

        if((distancex * distancex + distancey * distancey) < (ball.getRadius() * ball.getRadius())){
            Log.d("DISTANCE COMPARE: ", "" + (distancex * distancex + distancey * distancey) + " < " + (ball.getRadius() * ball.getRadius()));
            return true;
        }else{
            return false;
        }
    }

    private static Point getWallUpperLeft(Rect rectangle) {
        return new Point(rectangle.left,rectangle.top);
    }


}
