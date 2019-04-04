package com.example.mazegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

public class CollisionHandler {

    public static boolean hascollided(BallHandler ball, Rect rectangle){
        Point rectpos = getWallUpperLeft(rectangle);
        float centerx = ball.getxPos() + ball.getRadius();
        float centery = ball.getyPos() + ball.getRadius();
        float nearestx = Math.max(rectpos.x, Math.min(centerx, rectpos.x + rectangle.width()));
        float nearesty = Math.max(rectpos.y, Math.min(centery, rectpos.y + rectangle.height()));
        float distancex = centerx - nearestx;
        float distancey = centery - nearesty;

        return (distancex * distancex + distancey * distancey) < (ball.getRadius() * ball.getRadius());
    }

    private static Point getWallUpperLeft(Rect rectangle) {
        return new Point(rectangle.left,rectangle.top);
    }


}
