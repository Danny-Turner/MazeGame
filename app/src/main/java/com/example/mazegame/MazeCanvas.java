package com.example.mazegame;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
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
    private ArrayList<SendEndGame> listeners;

    public MazeCanvas(Context context, int displayWidth, int displayHeight, Maze maze, BallHandler ballHandler) {
        super(context);
        listeners = new ArrayList<>();
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
        ballHandler.setRadius(radius_float/2);
        ballHandler.setMaze(this);
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
        for (int i = 0; i < maze.getWalls().size(); i++) {
            canvas.drawRect(getRect(maze.getWalls().get(i)),wallPaint);
        }
    }

    public Maze getMaze(){return this.maze;}

    public Rect getRect(MazeWall wall) {
        if (wall.getDirection() == Orientation.horizontal) {
            return new Rect(wall.getColumn()*wallLength,wall.getRow()*wallLength,
                    (wall.getColumn()+1)*wallLength, wall.getRow()*wallLength+wallThickness);
        }
        return new Rect(wall.getColumn()*wallLength-1,wall.getRow()*wallLength,
                wall.getColumn()*wallLength+wallThickness, (wall.getRow()+1)*wallLength);
    }


   private void checkGameOver() {
       if (ballHandler.getyPos() > maze.getHeight()* wallLength) {
           gameOver = true;
       }
   }

   private void endGame() {
        timer.stop();
        for(SendEndGame listener: listeners){
            listener.sendTimer(timer, getContext());
        }

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

   public void addAsEndGameListener(SendEndGame listener){
        this.listeners.add(listener);
   }


}

