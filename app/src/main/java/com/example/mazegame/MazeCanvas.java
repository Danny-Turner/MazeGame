package com.example.mazegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mazegame.MazeCreation.Maze;
import com.example.mazegame.MazeCreation.MazeWall;
import com.example.mazegame.MazeCreation.Orientation;

import java.util.ArrayList;

public class MazeCanvas extends View {

    private BallHandler ballHandler;
    private int wallLength, wallThickness;
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
        timerPaint.setColor(Color.WHITE );
        timerPaint.setTextSize(60f);
        timerPaint.setTextAlign(Paint.Align.CENTER) ;
        timer = new Timer();
        timer.start();
        this.maze = maze;
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
        setBallstartPosition();
        ball = Bitmap.createScaledBitmap(ballSrc, length, length, true);
    }

    private void setBallstartPosition(){
        float temp_pos = wallThickness + ballHandler.getRadius();
        ballHandler.setxPos(temp_pos);
        ballHandler.setyPos(temp_pos);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMaze(maze,canvas);
        canvas.drawBitmap(ball, ballHandler.getxPos(), ballHandler.getyPos(), null);
        canvas.drawText(timer.displayTime(),canvas.getWidth()/2,timerPaint.descent()*5,timerPaint);
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
            return new Rect(wall.getColumn()*wallLength - (int)(wallThickness/2),
                    wall.getRow()*wallLength, (wall.getColumn()+1)*wallLength + (int)(wallThickness/2),
                    wall.getRow()*wallLength+wallThickness);
        }
        return new Rect(wall.getColumn()*wallLength - (int)(wallThickness/2),
                wall.getRow()*wallLength, wall.getColumn()*wallLength + (int)(wallThickness/2),
                (wall.getRow()+1)*wallLength);
    }


   private void checkGameOver() {
       if (ballHandler.getyPos() > maze.getHeight()* wallLength) {
           gameOver = true;
       }
   }

   private void endGame() {
        timer.stop();
        for(SendEndGame listener: listeners){
            listener.sendTimer(timer);
        }
    }

   public void addAsEndGameListener(SendEndGame listener){
        this.listeners.add(listener);
   }

}

