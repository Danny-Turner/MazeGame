package com.example.mazegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;

import java.util.ArrayList;
import java.util.Stack;

public class MazeCanvas extends View {

    private BallHandler ballHandler;
    private int displayWidth, displayHeight, wallLength, wallThickness;
    private Paint wallPaint,timerPaint;
    private Maze maze;
    private Bitmap ball;
    private Chronometer timer;


    public MazeCanvas(Context context, int displayWidth, int displayHeight, Maze maze, BallHandler ballHandler) {
        super(context);
        wallPaint = new Paint();
        timerPaint = new Paint();
        timerPaint.setColor(Color.WHITE);
        timerPaint.setTextSize(50f);
        timer = new Chronometer(context);
        //timer.start();
        this.maze = maze;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.ballHandler = ballHandler;
        int horizontalWallLength = (int) displayWidth / maze.getWidth();
        int verticalWallLength = (int) displayHeight / maze.getHeight();
        wallLength = Math.min(horizontalWallLength,verticalWallLength);
        wallThickness = (int) (wallLength /10);
        createBall();

    }

    private void createBall(){
        Bitmap ballSrc = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        int length = wallLength/2;
        float radius_float = length;
        Log.d("RADIUS", ""+radius_float);
        ballHandler.setRadius(radius_float/2);
        //TODO: change ball width in ball handler
        ball = Bitmap.createScaledBitmap(ballSrc, length, length, true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMaze(maze,canvas);
        canvas.drawBitmap(ball, ballHandler.getxPos(), ballHandler.getyPos(), null);
        timer.getDisplay();
        canvas.drawText(String.valueOf(SystemClock.elapsedRealtime()-timer.getBase()),50,50,timerPaint);
        //Log.e("test",timer.getText().toString());
        invalidate();
    }


    private void drawMaze(Maze m, Canvas canvas) {
        wallPaint.setColor(Color.WHITE);
        wallPaint.setStrokeWidth(wallThickness);
        Stack<Collided> collideds = new Stack<>();
        for (int i = 0; i < maze.getWalls().size(); i++) {
            Collided col = CollisionHandler.hascollided(ballHandler, getRect(maze.getWalls().get(i)));
            if(col.isHasCollided()){
                Paint paint = new Paint();
                paint.setColor(Color.GREEN);
                canvas.drawRect(getRect(maze.getWalls().get(i)),paint);
                collideds.push(col);
               // Log.d("COLLIDED", "ball: " + ballHandler.getxPos() +", " + ballHandler.getyPos() + " wall: "
               // + point.x + ", " + point.y);
            }else{
                canvas.drawRect(getRect(maze.getWalls().get(i)),wallPaint);
            }


           // Log.d("COLLISION", "hasCollided: "+col);
        }
        ballHandler.addCollisions(collideds);
    }

    private Rect getRect(MazeWall wall) {
        if (wall.getDirection() == Orientation.horizontal) {
            return new Rect(wall.getColumn()*wallLength,wall.getRow()*wallLength,
                    (wall.getColumn()+1)*wallLength, wall.getRow()*wallLength+wallThickness);
        }
        return new Rect(wall.getColumn()*wallLength-1,wall.getRow()*wallLength,
                wall.getColumn()*wallLength+wallThickness, (wall.getRow()+1)*wallLength);
    }

    private Point getWallUpperLeft(MazeWall wall) {
        Rect rectangle = getRect(wall);
        return new Point(rectangle.left,rectangle.top);
    }

    private Point getWallUpperRight(MazeWall wall) {
        Rect rectangle = getRect(wall);
        return new Point(rectangle.right,rectangle.top);
    }

    private Point getWallBottomLeft(MazeWall wall) {
        Rect rectangle = getRect(wall);
        return new Point(rectangle.left,rectangle.bottom);
    }

    private Point getWallBottomRight(MazeWall wall) {
        Rect rectangle = getRect(wall);
        return new Point(rectangle.right,rectangle.bottom);
    }


}

