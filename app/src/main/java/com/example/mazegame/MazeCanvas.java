package com.example.mazegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MazeCanvas extends View {

    BallHandler ballHandler;
    int displayWidth, displayHeight, width, height;
    int verticalWallLength, horizontalWallLength, wallLength,wallThickness;
    Paint wallPaint;
    Maze maze;
    Bitmap ball;


    public MazeCanvas(Context context, int displayWidth, int displayHeight, Maze maze, BallHandler ballHandler) {
        super(context);
        width = maze.getWidth();
        height = maze.getHeight();
        wallThickness = 3;  //3 is testing value. Will need to be calculated later
        wallPaint = new Paint();
        this.maze = maze;
        this.ballHandler = ballHandler;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        horizontalWallLength = (int) displayWidth /width;
        verticalWallLength = (int) displayHeight /height;
        createBall();
        wallLength = Math.min(horizontalWallLength,verticalWallLength);
    }





    private void createBall(){
        Bitmap ballSrc = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        final int width = 50;
        final int height = 50;
        ball = Bitmap.createScaledBitmap(ballSrc, width, height, true);
    }





    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //setUpCanvasDimensions();
        //horizontalWallLength = (int) diplayWidth /width;
        //verticalWallLength = (int) displayHeight /height;
        //wallLength = Math.min(horizontalWallLength,verticalWallLength);

        drawMaze(maze,canvas);
        canvas.drawBitmap(ball, ballHandler.getxPos(), ballHandler.getyPos(), null);
        invalidate();
    }

    private void setUpCanvasDimensions(){
        displayWidth = getWidth();
        displayHeight = getHeight();
    }


    private void drawMaze(Maze m, Canvas canvas) {
        wallPaint.setColor(Color.WHITE);
        wallPaint.setStrokeWidth(wallThickness);
        for (int i = 0; i < maze.getWalls().size(); i++) {
            if (maze.getWalls().get(i).getDirection() == Orientation.horizontal) {
                drawHorizontalWall(maze.getWalls().get(i),canvas);
            } else if(maze.getWalls().get(i).getDirection() == Orientation.vertical) {
                drawVerticalWall(maze.getWalls().get(i),canvas);
            }
        }
    }



    private void drawHorizontalWall(MazeWall w, Canvas c){
        c.drawRect(w.getColumn()*wallLength,w.getRow()*wallLength,
                (w.getColumn()+1)*wallLength, w.getRow()*wallLength+wallThickness,wallPaint);
    }

    private void drawVerticalWall(MazeWall w, Canvas c){
        c.drawRect(w.getColumn()*wallLength-1,w.getRow()*wallLength,
                w.getColumn()*wallLength+wallThickness, (w.getRow()+1)*wallLength,wallPaint);
    }
}
