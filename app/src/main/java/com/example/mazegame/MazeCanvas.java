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
import android.view.Gravity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;

public class MazeCanvas extends View {

    private BallHandler ballHandler;
    private int displayWidth, displayHeight, wallLength, wallThickness;
    private Paint wallPaint,timerPaint;
    private Maze maze;
    private Bitmap ball;
    private Timer timer;
    private boolean gameOver;
    private TextView username_input;

    public MazeCanvas(Context context, int displayWidth, int displayHeight, Maze maze, BallHandler ballHandler) {
        super(context);
        wallPaint = new Paint();
        timerPaint = new Paint();
        timerPaint.setColor(Color.WHITE);
        timerPaint.setTextSize(50f);
        timer = new Timer();
        timer.start();
        this.maze = maze;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.ballHandler = ballHandler;
        int horizontalWallLength = (int) displayWidth / maze.getWidth();
        int verticalWallLength = (int) displayHeight / maze.getHeight();
        wallLength = Math.min(horizontalWallLength,verticalWallLength);
        wallThickness = (int) (wallLength /10);
        createBall();
        gameOver = false;

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
        canvas.drawText(timer.displayTime(),50,50,timerPaint);
        checkGameOver();
        if (!gameOver) {
            invalidate();
        } else {endGame();}

    }


    private void drawMaze(Maze m, Canvas canvas) {
        wallPaint.setColor(Color.WHITE);
        wallPaint.setStrokeWidth(wallThickness);
        Stack<Collided> collideds = new Stack<>();
        float ballx = ballHandler.getxPos();
        float bally = ballHandler.getyPos();
        for (int i = 0; i < maze.getWalls().size(); i++) {
            Collided col = CollisionHandler.hascollided(ballx, bally, wallLength/4, getRect(maze.getWalls().get(i)));
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

   private void checkGameOver() {
       if (ballHandler.getyPos() > maze.getHeight()* wallLength) {
           gameOver = true;
       }
   }

   private void endGame() {
        timer.stop();

       /*
         Utilize a dialog box to retrieve a username from the user
         Use timer.getElapsedTime() for the 'score' portion of the HighScore object
         call ScorePage.addNewScore() which takes the String username and the raw Long from the timer
       */

       /*
       PopupWindow popup = new PopupWindow();
       popup.setOnDismissListener(new PopupWindow.OnDismissListener(){
           @Override
           public void onDismiss() {
           }
       });
       popup.showAtLocation(this, Gravity.CENTER, 50,50);
        */


   }

}

