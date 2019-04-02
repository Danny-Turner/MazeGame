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
import android.util.Log;
import android.view.View;

public class MazeCanvas extends View {

    private BallHandler ballHandler;
    private int wallLength, wallThickness;
    private Paint wallPaint;
    private Maze maze;
    private Bitmap ball;


    public MazeCanvas(Context context, int displayWidth, int displayHeight, Maze maze, BallHandler ballHandler) {
        super(context);
        wallThickness = 3;  //3 is testing value. Will need to be calculated later
        wallPaint = new Paint();
        this.maze = maze;
        this.ballHandler = ballHandler;
        int horizontalWallLength = (int) displayWidth / maze.getWidth();
        int verticalWallLength = (int) displayHeight / maze.getHeight();
        wallLength = Math.min(horizontalWallLength,verticalWallLength);
        createBall();

    }

    private void createBall(){
        Bitmap ballSrc = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        final int width = wallLength/2;
        final int height = wallLength/2;
        ball = Bitmap.createScaledBitmap(ballSrc, width, height, true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMaze(maze,canvas);
        canvas.drawBitmap(ball, ballHandler.getxPos(), ballHandler.getyPos(), null);
        invalidate();
    }


    private void drawMaze(Maze m, Canvas canvas) {
        wallPaint.setColor(Color.WHITE);
        wallPaint.setStrokeWidth(wallThickness);
        for (int i = 0; i < maze.getWalls().size(); i++) {
            canvas.drawRect(getRect(maze.getWalls().get(i)),wallPaint);
        }
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

